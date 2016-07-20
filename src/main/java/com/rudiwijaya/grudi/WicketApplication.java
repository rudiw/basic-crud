package com.rudiwijaya.grudi;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.CompilationLevel;
import com.rudiwijaya.grudi.pages.DedicatedLoginPage;
import com.rudiwijaya.grudi.pages.FinishPage;
import com.rudiwijaya.grudi.pages.HomePage;
import com.rudiwijaya.grudi.pages.RegisterPersonPage;
import com.rudiwijaya.grudi.pages.RegisterTeamPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.DefaultThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.rudiwijaya.base.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	
	private static final Logger log = LoggerFactory
			.getLogger(WicketApplication.class);
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response) {
//		return super.newSession(request, response);
		final WicketSession session = new WicketSession(request);
		return session;
	}
	
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		
		log.info("Initializing for wicketApplication..");
		
		//add spring injector
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		
		//add listeners
		getRequestCycleListeners().add(new NotLoggedInRequestCycleListener());
		
		getApplicationSettings().setUploadProgressUpdatesEnabled(true);

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        configureWebjars();
        configureBootstrap();

        optimizeForWebPerformance();
        
        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            securePackageResourceGuard.addPattern("+*.woff2");
        }

//        WicketSource.configure(this);
        
        mountPages();
	}
	
	/**
	 * Mount page based on {@link ManageMountPath} annotation.
	 * @param page
	 */
	private void mountPage(Class<? extends Page> page) {
		final MountPath mountPath = Preconditions.checkNotNull(page.getAnnotation(MountPath.class),
				"Page class %s does not have @MountPath/@ManageMountPath/@OpenMountPath annotation", page.getName());
		mountPage(mountPath.value(), page);
	}
	
	private void mountPages() {
		mountPage(DedicatedLoginPage.class);
		mountPage(RegisterPersonPage.class);
		mountPage(RegisterTeamPage.class);
		mountPage(FinishPage.class);
	}
	
	/**
	 * See https://github.com/l0rdn1kk0n/wicket-webjars
	 */
	private void configureWebjars() {
        // install 2 default collector instances 
        // (FileAssetPathCollector(WEBJARS_PATH_PREFIX), JarAssetPathCollector)
        // and a webjars resource finder.
        final WebjarsSettings settings = new WebjarsSettings();

        // register vfs collector to use webjars on jboss (you don't need to add maven dependency)
//        Set<AssetPathCollector> collectors = Sets.newHashSet(settings.assetPathCollectors());
//        collectors.add(new VfsJarAssetPathCollector());
//        settings.assetPathCollectors(collectors.toArray(new AssetPathCollector[collectors.size()]));

        WicketWebjars.install(this, settings);
	}
	
	
	/**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
    	
        final IBootstrapSettings settings = new BootstrapSettings();
//        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Cosmo);
        final ThemeProvider themeProvider = new DefaultThemeProvider();

        settings.setJsResourceFilterName("footer-container").setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
        BootstrapLess.install(this);
    }
    
    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(
                    "-v-",
                    new CachingResourceVersion(new Adler32ResourceVersion())
            ));

            getResourceSettings().setJavaScriptCompressor(new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        } else {
            getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
        }

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
        getRequestCycleSettings().setRenderStrategy(RenderStrategy.ONE_PASS_RENDER);
    }
    
}
