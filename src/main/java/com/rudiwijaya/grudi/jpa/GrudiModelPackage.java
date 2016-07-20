package com.rudiwijaya.grudi.jpa;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.texo.model.ModelFactory;
import org.eclipse.emf.texo.model.ModelPackage;
import org.eclipse.emf.texo.model.ModelResolver;
import org.eclipse.emf.texo.utils.ModelUtils;

/**
 * The <b>Package</b> for the model '<em><b>grudi</b></em>'. It contains
 * initialization code and access to the Factory to instantiate types of this
 * package.
 *
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class GrudiModelPackage extends ModelPackage {

	/**
	 * Is set when the package has been initialized.
	 * 
	 * @generated
	 */
	private static boolean isInitialized = false;

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String NS_URI = "http://www.rudiwijaya.com/grudi.jpa/";

	/**
	 * The {@link ModelFactory} for this package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static final GrudiModelFactory MODELFACTORY = new GrudiModelFactory();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int GENDER_CLASSIFIER_ID = 4;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMPERSONKIND_CLASSIFIER_ID = 5;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_CLASSIFIER_ID = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_ID_FEATURE_ID = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_USERNAME_FEATURE_ID = 1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_EMAIL_FEATURE_ID = 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_PASSWORD_FEATURE_ID = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_NAME_FEATURE_ID = 4;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_PHONENUMBER_FEATURE_ID = 5;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_GENDER_FEATURE_ID = 6;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_ADDRESS_FEATURE_ID = 7;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSON_VERSIONNUMBER_FEATURE_ID = 8;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAM_CLASSIFIER_ID = 1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAM_ID_FEATURE_ID = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAM_NAME_FEATURE_ID = 1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAM_LINES_FEATURE_ID = 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAM_VERSIONNUMBER_FEATURE_ID = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_CLASSIFIER_ID = 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_ID_FEATURE_ID = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_KIND_FEATURE_ID = 1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_TEAM_FEATURE_ID = 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_PERSON_FEATURE_ID = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int TEAMLINE_VERSIONNUMBER_FEATURE_ID = 4;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_CLASSIFIER_ID = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_ID_FEATURE_ID = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_USERNAME_FEATURE_ID = 1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_NAME_FEATURE_ID = 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_PHONENUMBER_FEATURE_ID = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int PERSONINFO_GENDER_FEATURE_ID = 4;

	/**
	 * The static member with the instance of this {@link ModelPackage}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final GrudiModelPackage INSTANCE = initialize();

	/**
	 * Initializes this {@link ModelPackage}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @return an initialized instance of this class
	 *
	 * @generated
	 */
	public static GrudiModelPackage initialize() {

		if (isInitialized) {
			return (GrudiModelPackage) ModelResolver.getInstance().getModelPackage(NS_URI);
		}

		final GrudiModelPackage modelPackage = new GrudiModelPackage();

		ModelResolver.getInstance().registerModelPackage(modelPackage);

		// read the model from the ecore file, the EPackage is registered in the
		// EPackage.Registry
		// see the ModelResolver getEPackageRegistry method
		ModelUtils.readEPackagesFromFile(modelPackage);

		isInitialized = true;

		// force the initialization of the EFactory proxy
		modelPackage.getEPackage();

		// register the relation between a Class and its EClassifier
		ModelResolver.getInstance().registerClassModelMapping(Person.class, modelPackage.getPersonEClass(),
				modelPackage);
		ModelResolver.getInstance().registerClassModelMapping(Team.class, modelPackage.getTeamEClass(), modelPackage);
		ModelResolver.getInstance().registerClassModelMapping(TeamLine.class, modelPackage.getTeamLineEClass(),
				modelPackage);
		ModelResolver.getInstance().registerClassModelMapping(PersonInfo.class, modelPackage.getPersonInfoEClass(),
				modelPackage);

		ModelResolver.getInstance().registerClassModelMapping(Gender.class, modelPackage.getGenderEEnum(),
				modelPackage);
		ModelResolver.getInstance().registerClassModelMapping(TeamPersonKind.class,
				modelPackage.getTeamPersonKindEEnum(), modelPackage);

		// and return ourselves
		return modelPackage;
	}

	/**
	 * Returns the {@link ModelFactory} of this ModelPackage. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return the {@link GrudiModelFactory} instance.
	 * @generated
	 */
	@Override
	public GrudiModelFactory getModelFactory() {
		return MODELFACTORY;
	}

	/**
	 * Returns the nsUri of the {@link EPackage} managed by this Package
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return the nsUri of the EPackage
	 * @generated
	 */
	@Override
	public String getNsURI() {
		return NS_URI;
	}

	/**
	 * Returns the name of the ecore file containing the ecore model of the
	 * {@link EPackage} managed here. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @return the name of the ecore file
	 * @generated
	 */
	@Override
	public String getEcoreFileName() {
		return "grudi.ecore";
	}

	/**
	 * Returns the {@link EClass} '<em><b>Person</b></em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EClass} '<em><b>Person</b></em>'
	 * @generated
	 */
	public EClass getPersonEClass() {
		return (EClass) getEPackage().getEClassifiers().get(PERSON_CLASSIFIER_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.id</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.id</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Id() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_ID_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.username</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.username</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Username() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_USERNAME_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.email</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.email</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Email() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_EMAIL_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.password</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.password</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Password() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_PASSWORD_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.name</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.name</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Name() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_NAME_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>Person.phoneNumber</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.phoneNumber</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_PhoneNumber() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_PHONENUMBER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.gender</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.gender</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Gender() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_GENDER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Person.address</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.address</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_Address() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_ADDRESS_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>Person.versionNumber</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Person.versionNumber</b></em>'.
	 * @generated
	 */
	public EAttribute getPerson_VersionNumber() {
		return (EAttribute) getPersonEClass().getEAllStructuralFeatures().get(PERSON_VERSIONNUMBER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EClass} '<em><b>Team</b></em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EClass} '<em><b>Team</b></em>'
	 * @generated
	 */
	public EClass getTeamEClass() {
		return (EClass) getEPackage().getEClassifiers().get(TEAM_CLASSIFIER_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Team.id</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Team.id</b></em>'.
	 * @generated
	 */
	public EAttribute getTeam_Id() {
		return (EAttribute) getTeamEClass().getEAllStructuralFeatures().get(TEAM_ID_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Team.name</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Team.name</b></em>'.
	 * @generated
	 */
	public EAttribute getTeam_Name() {
		return (EAttribute) getTeamEClass().getEAllStructuralFeatures().get(TEAM_NAME_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>Team.lines</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Team.lines</b></em>'.
	 * @generated
	 */
	public EReference getTeam_Lines() {
		return (EReference) getTeamEClass().getEAllStructuralFeatures().get(TEAM_LINES_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>Team.versionNumber</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>Team.versionNumber</b></em>'.
	 * @generated
	 */
	public EAttribute getTeam_VersionNumber() {
		return (EAttribute) getTeamEClass().getEAllStructuralFeatures().get(TEAM_VERSIONNUMBER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EClass} '<em><b>TeamLine</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EClass} '<em><b>TeamLine</b></em>'
	 * @generated
	 */
	public EClass getTeamLineEClass() {
		return (EClass) getEPackage().getEClassifiers().get(TEAMLINE_CLASSIFIER_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>TeamLine.id</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>TeamLine.id</b></em>'.
	 * @generated
	 */
	public EAttribute getTeamLine_Id() {
		return (EAttribute) getTeamLineEClass().getEAllStructuralFeatures().get(TEAMLINE_ID_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>TeamLine.kind</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>TeamLine.kind</b></em>'.
	 * @generated
	 */
	public EAttribute getTeamLine_Kind() {
		return (EAttribute) getTeamLineEClass().getEAllStructuralFeatures().get(TEAMLINE_KIND_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>TeamLine.team</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>TeamLine.team</b></em>'.
	 * @generated
	 */
	public EReference getTeamLine_Team() {
		return (EReference) getTeamLineEClass().getEAllStructuralFeatures().get(TEAMLINE_TEAM_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>TeamLine.person</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>TeamLine.person</b></em>'.
	 * @generated
	 */
	public EReference getTeamLine_Person() {
		return (EReference) getTeamLineEClass().getEAllStructuralFeatures().get(TEAMLINE_PERSON_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>TeamLine.versionNumber</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>TeamLine.versionNumber</b></em>'.
	 * @generated
	 */
	public EAttribute getTeamLine_VersionNumber() {
		return (EAttribute) getTeamLineEClass().getEAllStructuralFeatures().get(TEAMLINE_VERSIONNUMBER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EClass} '<em><b>PersonInfo</b></em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EClass} '<em><b>PersonInfo</b></em>'
	 * @generated
	 */
	public EClass getPersonInfoEClass() {
		return (EClass) getEPackage().getEClassifiers().get(PERSONINFO_CLASSIFIER_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>PersonInfo.id</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>PersonInfo.id</b></em>'.
	 * @generated
	 */
	public EAttribute getPersonInfo_Id() {
		return (EAttribute) getPersonInfoEClass().getEAllStructuralFeatures().get(PERSONINFO_ID_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>PersonInfo.userName</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>PersonInfo.userName</b></em>'.
	 * @generated
	 */
	public EAttribute getPersonInfo_UserName() {
		return (EAttribute) getPersonInfoEClass().getEAllStructuralFeatures().get(PERSONINFO_USERNAME_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>PersonInfo.name</b></em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>PersonInfo.name</b></em>'.
	 * @generated
	 */
	public EAttribute getPersonInfo_Name() {
		return (EAttribute) getPersonInfoEClass().getEAllStructuralFeatures().get(PERSONINFO_NAME_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '
	 * <em><b>PersonInfo.phoneNumber</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>PersonInfo.phoneNumber</b></em>'.
	 * @generated
	 */
	public EAttribute getPersonInfo_PhoneNumber() {
		return (EAttribute) getPersonInfoEClass().getEAllStructuralFeatures().get(PERSONINFO_PHONENUMBER_FEATURE_ID);
	}

	/**
	 * Returns the {@link EStructuralFeature} '<em><b>PersonInfo.gender</b></em>
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the {@link EStructuralFeature}: '
	 *         <em><b>PersonInfo.gender</b></em>'.
	 * @generated
	 */
	public EAttribute getPersonInfo_Gender() {
		return (EAttribute) getPersonInfoEClass().getEAllStructuralFeatures().get(PERSONINFO_GENDER_FEATURE_ID);
	}

	/**
	 * Returns the EEnum '<em><b>Gender</b></em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return an instance of the EEnum representing '<em><b>Gender</b></em>'
	 * @generated
	 */
	public EEnum getGenderEEnum() {
		return (EEnum) getEPackage().getEClassifiers().get(GENDER_CLASSIFIER_ID);
	}

	/**
	 * Returns the EEnum '<em><b>TeamPersonKind</b></em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the EEnum representing '
	 *         <em><b>TeamPersonKind</b></em>'
	 * @generated
	 */
	public EEnum getTeamPersonKindEEnum() {
		return (EEnum) getEPackage().getEClassifiers().get(TEAMPERSONKIND_CLASSIFIER_ID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param eClassifier
	 *            the {@link EClassifier}
	 * @return the class implementing a specific {@link EClass}.
	 * @generated
	 */
	@Override
	public Class<?> getEClassifierClass(EClassifier eClassifier) {
		switch (eClassifier.getClassifierID()) {
		case PERSON_CLASSIFIER_ID:
			return Person.class;
		case TEAM_CLASSIFIER_ID:
			return Team.class;
		case TEAMLINE_CLASSIFIER_ID:
			return TeamLine.class;
		case PERSONINFO_CLASSIFIER_ID:
			return PersonInfo.class;
		case GENDER_CLASSIFIER_ID:
			return Gender.class;
		case TEAMPERSONKIND_CLASSIFIER_ID:
			return TeamPersonKind.class;
		default:
			throw new IllegalArgumentException("The EClassifier '" + eClassifier + "' is not defined in this EPackage");
		}
	}
}
