package com.rudiwijaya.grudi.jpa;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.texo.model.AbstractModelFeatureMapEntry;
import org.eclipse.emf.texo.model.AbstractModelObject;
import org.eclipse.emf.texo.model.ModelFactory;
import org.eclipse.emf.texo.model.ModelFeatureMapEntry;
import org.eclipse.emf.texo.model.ModelObject;
import org.eclipse.emf.texo.model.ModelPackage;

/**
 * The <b>{@link ModelFactory}</b> for the types of this model: grudi. It
 * contains code to create instances {@link ModelObject} wrappers and instances
 * for EClasses and convert objects back and forth from their String (XML)
 * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class GrudiModelFactory implements ModelFactory {

	/**
	 * Creates an instance for an {@link EClass} <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param eClass
	 *            creates a Object instance for this EClass
	 * @return an object representing the eClass
	 * @generated
	 */
	public Object create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case GrudiModelPackage.PERSON_CLASSIFIER_ID:
			return createPerson();
		case GrudiModelPackage.TEAM_CLASSIFIER_ID:
			return createTeam();
		case GrudiModelPackage.TEAMLINE_CLASSIFIER_ID:
			return createTeamLine();
		case GrudiModelPackage.PERSONINFO_CLASSIFIER_ID:
			return createPersonInfo();
		default:
			throw new IllegalArgumentException(
					"The EClass '" + eClass.getName() + "' is not a valid EClass for this EPackage");
		}
	}

	/**
	 * Wraps an object in a {@link ModelObject}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @param eClass
	 *            the EClass of the object
	 * @param adaptee
	 *            the object being wrapped/adapted
	 * @return the wrapper {@link ModelObject}
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> ModelObject<T> createModelObject(EClass eClass, T adaptee) {
		ModelObject<Object> modelObject = null;
		switch (eClass.getClassifierID()) {
		case GrudiModelPackage.PERSON_CLASSIFIER_ID:
			modelObject = new PersonModelObject();
			break;
		case GrudiModelPackage.TEAM_CLASSIFIER_ID:
			modelObject = new TeamModelObject();
			break;
		case GrudiModelPackage.TEAMLINE_CLASSIFIER_ID:
			modelObject = new TeamLineModelObject();
			break;
		case GrudiModelPackage.PERSONINFO_CLASSIFIER_ID:
			modelObject = new PersonInfoModelObject();
			break;
		default:
			throw new IllegalArgumentException("The EClass '" + eClass + "' is not defined in this EPackage");
		}
		modelObject.setTarget(adaptee);
		return (ModelObject<T>) modelObject;
	}

	/**
	 * Creates a feature map entry instance for a certain EStructuralFeature.
	 *
	 * @param eFeature
	 *            the feature map feature
	 * @return the pojo feature map entry
	 * @generated
	 */
	public Object createFeatureMapEntry(EStructuralFeature eFeature) {
		throw new IllegalArgumentException(
				"The EStructuralFeature '" + eFeature + "' is not a valid feature map in this EPackage");
	}

	/**
	 * Wraps a feature map entry pojo in a {@link AbstractModelFeatureMapEntry}.
	 * If the feature map entry is null then a new one is created and <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param eFeature
	 *            the feature map feature of the object
	 * @param adaptee
	 *            the pojo feature map entry being wrapped/adapted
	 * @return the wrapper {@link ModelFeatureMapEntry}
	 * @generated
	 */
	public ModelFeatureMapEntry<?> createModelFeatureMapEntry(EStructuralFeature eFeature, Object adaptee) {
		throw new IllegalArgumentException(
				"The EStructuralFeature '" + eFeature + "' is not a valid feature map in this EPackage");
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the model object representing the EClass Person
	 * @generated
	 */
	public Person createPerson() {
		return new Person();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the model object representing the EClass Team
	 * @generated
	 */
	public Team createTeam() {
		return new Team();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the model object representing the EClass TeamLine
	 * @generated
	 */
	public TeamLine createTeamLine() {
		return new TeamLine();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return an instance of the model object representing the EClass
	 *         PersonInfo
	 * @generated
	 */
	public PersonInfo createPersonInfo() {
		return new PersonInfo();
	}

	/**
	 * Converts an instance of an {@link EDataType} to a String. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param eDataType
	 *            the {@link EDataType} defining the type
	 * @param value
	 *            the object to convert, if the value is null then null is
	 *            returned.
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String value) {
		switch (eDataType.getClassifierID()) {
		case GrudiModelPackage.GENDER_CLASSIFIER_ID:
			return createGenderFromString(value);
		case GrudiModelPackage.TEAMPERSONKIND_CLASSIFIER_ID:
			return createTeamPersonKindFromString(value);
		default:
			throw new IllegalArgumentException("The EDatatype '" + eDataType + "' is not defined in this EPackage");
		}
	}

	/**
	 * Converts an instance of an {@link EDataType} to a String. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param eDataType
	 *            the {@link EDataType} defining the type
	 * @param value
	 *            the object to convert, if value == null then null is returned
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object value) {
		switch (eDataType.getClassifierID()) {
		case GrudiModelPackage.GENDER_CLASSIFIER_ID:
			return convertGenderToString((Gender) value);
		case GrudiModelPackage.TEAMPERSONKIND_CLASSIFIER_ID:
			return convertTeamPersonKindToString((TeamPersonKind) value);
		default:
			throw new IllegalArgumentException("The EDatatype '" + eDataType + "' is not defined in this EPackage.");
		}
	}

	/**
	 * Converts the EDataType: Gender to a String. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the object to convert
	 * @return the String representing the value, if value == null then null is
	 *         returned
	 * @generated
	 */
	public String convertGenderToString(Gender value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	/**
	 * Creates an instance of the EDataType: Gender from a String. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the string value to convert to an object
	 * @return the instance of the data type, if value == null then null is
	 *         returned
	 * @generated
	 */
	public Gender createGenderFromString(String value) {
		if (value == null) {
			return null;
		}
		return Gender.get(value);
	}

	/**
	 * Converts the EDataType: TeamPersonKind to a String. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the object to convert
	 * @return the String representing the value, if value == null then null is
	 *         returned
	 * @generated
	 */
	public String convertTeamPersonKindToString(TeamPersonKind value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	/**
	 * Creates an instance of the EDataType: TeamPersonKind from a String. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the string value to convert to an object
	 * @return the instance of the data type, if value == null then null is
	 *         returned
	 * @generated
	 */
	public TeamPersonKind createTeamPersonKindFromString(String value) {
		if (value == null) {
			return null;
		}
		return TeamPersonKind.get(value);
	}

	/**
	 * The adapter/wrapper for the EClass '<em><b>Person</b></em>'.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param <E>
	 *            the domain model java class
	 *
	 * @generated
	 */
	public static class PersonModelObject<E extends Person> extends AbstractModelObject<E> {
		/**
		 * @generated
		 */
		public EClass eClass() {
			return GrudiModelPackage.INSTANCE.getPersonEClass();
		}

		/**
		 * @generated
		 */
		public ModelPackage getModelPackage() {
			return GrudiModelPackage.INSTANCE;
		}

		/**
		 * @generated
		 */
		@Override
		public Class<?> getTargetClass() {
			return Person.class;
		}

		/**
		 * @generated
		 */
		@Override
		public Object eGet(EStructuralFeature eStructuralFeature) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.PERSON_ID_FEATURE_ID:
				return getTarget().getId();
			case GrudiModelPackage.PERSON_USERNAME_FEATURE_ID:
				return getTarget().getUsername();
			case GrudiModelPackage.PERSON_EMAIL_FEATURE_ID:
				return getTarget().getEmail();
			case GrudiModelPackage.PERSON_PASSWORD_FEATURE_ID:
				return getTarget().getPassword();
			case GrudiModelPackage.PERSON_NAME_FEATURE_ID:
				return getTarget().getName();
			case GrudiModelPackage.PERSON_PHONENUMBER_FEATURE_ID:
				return getTarget().getPhoneNumber();
			case GrudiModelPackage.PERSON_GENDER_FEATURE_ID:
				return getTarget().getGender();
			case GrudiModelPackage.PERSON_ADDRESS_FEATURE_ID:
				return getTarget().getAddress();
			case GrudiModelPackage.PERSON_VERSIONNUMBER_FEATURE_ID:
				return getTarget().getVersionNumber();
			default:
				return super.eGet(eStructuralFeature);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public void eSet(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.PERSON_ID_FEATURE_ID:
				getTarget().setId((Long) value);
				return;
			case GrudiModelPackage.PERSON_USERNAME_FEATURE_ID:
				getTarget().setUsername((String) value);
				return;
			case GrudiModelPackage.PERSON_EMAIL_FEATURE_ID:
				getTarget().setEmail((String) value);
				return;
			case GrudiModelPackage.PERSON_PASSWORD_FEATURE_ID:
				getTarget().setPassword((String) value);
				return;
			case GrudiModelPackage.PERSON_NAME_FEATURE_ID:
				getTarget().setName((String) value);
				return;
			case GrudiModelPackage.PERSON_PHONENUMBER_FEATURE_ID:
				getTarget().setPhoneNumber((String) value);
				return;
			case GrudiModelPackage.PERSON_GENDER_FEATURE_ID:
				getTarget().setGender((Gender) value);
				return;
			case GrudiModelPackage.PERSON_ADDRESS_FEATURE_ID:
				getTarget().setAddress((String) value);
				return;
			case GrudiModelPackage.PERSON_VERSIONNUMBER_FEATURE_ID:
				getTarget().setVersionNumber((Long) value);
				return;
			default:
				super.eSet(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eAddTo(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eAddTo(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eRemoveFrom(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eRemoveFrom(eStructuralFeature, value);
			}
		}
	}

	/**
	 * The adapter/wrapper for the EClass '<em><b>Team</b></em>'.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param <E>
	 *            the domain model java class
	 *
	 * @generated
	 */
	public static class TeamModelObject<E extends Team> extends AbstractModelObject<E> {
		/**
		 * @generated
		 */
		public EClass eClass() {
			return GrudiModelPackage.INSTANCE.getTeamEClass();
		}

		/**
		 * @generated
		 */
		public ModelPackage getModelPackage() {
			return GrudiModelPackage.INSTANCE;
		}

		/**
		 * @generated
		 */
		@Override
		public Class<?> getTargetClass() {
			return Team.class;
		}

		/**
		 * @generated
		 */
		@Override
		public Object eGet(EStructuralFeature eStructuralFeature) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.TEAM_ID_FEATURE_ID:
				return getTarget().getId();
			case GrudiModelPackage.TEAM_NAME_FEATURE_ID:
				return getTarget().getName();
			case GrudiModelPackage.TEAM_LINES_FEATURE_ID:
				return getTarget().getLines();
			case GrudiModelPackage.TEAM_VERSIONNUMBER_FEATURE_ID:
				return getTarget().getVersionNumber();
			default:
				return super.eGet(eStructuralFeature);
			}
		}

		/**
		 * @generated
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void eSet(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.TEAM_ID_FEATURE_ID:
				getTarget().setId((Long) value);
				return;
			case GrudiModelPackage.TEAM_NAME_FEATURE_ID:
				getTarget().setName((String) value);
				return;
			case GrudiModelPackage.TEAM_LINES_FEATURE_ID:
				getTarget().setLines((List<TeamLine>) value);
				return;
			case GrudiModelPackage.TEAM_VERSIONNUMBER_FEATURE_ID:
				getTarget().setVersionNumber((Long) value);
				return;
			default:
				super.eSet(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eAddTo(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			case GrudiModelPackage.TEAM_LINES_FEATURE_ID:
				return getTarget().addToLines((TeamLine) value);

			default:
				return super.eAddTo(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eRemoveFrom(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			case GrudiModelPackage.TEAM_LINES_FEATURE_ID:
				return getTarget().removeFromLines((TeamLine) value);

			default:
				return super.eRemoveFrom(eStructuralFeature, value);
			}
		}
	}

	/**
	 * The adapter/wrapper for the EClass '<em><b>TeamLine</b></em>'.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param <E>
	 *            the domain model java class
	 *
	 * @generated
	 */
	public static class TeamLineModelObject<E extends TeamLine> extends AbstractModelObject<E> {
		/**
		 * @generated
		 */
		public EClass eClass() {
			return GrudiModelPackage.INSTANCE.getTeamLineEClass();
		}

		/**
		 * @generated
		 */
		public ModelPackage getModelPackage() {
			return GrudiModelPackage.INSTANCE;
		}

		/**
		 * @generated
		 */
		@Override
		public Class<?> getTargetClass() {
			return TeamLine.class;
		}

		/**
		 * @generated
		 */
		@Override
		public Object eGet(EStructuralFeature eStructuralFeature) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.TEAMLINE_ID_FEATURE_ID:
				return getTarget().getId();
			case GrudiModelPackage.TEAMLINE_KIND_FEATURE_ID:
				return getTarget().getKind();
			case GrudiModelPackage.TEAMLINE_TEAM_FEATURE_ID:
				return getTarget().getTeam();
			case GrudiModelPackage.TEAMLINE_PERSON_FEATURE_ID:
				return getTarget().getPerson();
			case GrudiModelPackage.TEAMLINE_VERSIONNUMBER_FEATURE_ID:
				return getTarget().getVersionNumber();
			default:
				return super.eGet(eStructuralFeature);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public void eSet(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.TEAMLINE_ID_FEATURE_ID:
				getTarget().setId((String) value);
				return;
			case GrudiModelPackage.TEAMLINE_KIND_FEATURE_ID:
				getTarget().setKind((TeamPersonKind) value);
				return;
			case GrudiModelPackage.TEAMLINE_TEAM_FEATURE_ID:
				getTarget().setTeam((Team) value);
				return;
			case GrudiModelPackage.TEAMLINE_PERSON_FEATURE_ID:
				getTarget().setPerson((PersonInfo) value);
				return;
			case GrudiModelPackage.TEAMLINE_VERSIONNUMBER_FEATURE_ID:
				getTarget().setVersionNumber((Long) value);
				return;
			default:
				super.eSet(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eAddTo(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eAddTo(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eRemoveFrom(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eRemoveFrom(eStructuralFeature, value);
			}
		}
	}

	/**
	 * The adapter/wrapper for the EClass '<em><b>PersonInfo</b></em>'.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param <E>
	 *            the domain model java class
	 *
	 * @generated
	 */
	public static class PersonInfoModelObject<E extends PersonInfo> extends AbstractModelObject<E> {
		/**
		 * @generated
		 */
		public EClass eClass() {
			return GrudiModelPackage.INSTANCE.getPersonInfoEClass();
		}

		/**
		 * @generated
		 */
		public ModelPackage getModelPackage() {
			return GrudiModelPackage.INSTANCE;
		}

		/**
		 * @generated
		 */
		@Override
		public Class<?> getTargetClass() {
			return PersonInfo.class;
		}

		/**
		 * @generated
		 */
		@Override
		public Object eGet(EStructuralFeature eStructuralFeature) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.PERSONINFO_ID_FEATURE_ID:
				return getTarget().getId();
			case GrudiModelPackage.PERSONINFO_USERNAME_FEATURE_ID:
				return getTarget().getUserName();
			case GrudiModelPackage.PERSONINFO_NAME_FEATURE_ID:
				return getTarget().getName();
			case GrudiModelPackage.PERSONINFO_PHONENUMBER_FEATURE_ID:
				return getTarget().getPhoneNumber();
			case GrudiModelPackage.PERSONINFO_GENDER_FEATURE_ID:
				return getTarget().getGender();
			default:
				return super.eGet(eStructuralFeature);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public void eSet(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {
			case GrudiModelPackage.PERSONINFO_ID_FEATURE_ID:
				getTarget().setId((Long) value);
				return;
			case GrudiModelPackage.PERSONINFO_USERNAME_FEATURE_ID:
				getTarget().setUserName((String) value);
				return;
			case GrudiModelPackage.PERSONINFO_NAME_FEATURE_ID:
				getTarget().setName((String) value);
				return;
			case GrudiModelPackage.PERSONINFO_PHONENUMBER_FEATURE_ID:
				getTarget().setPhoneNumber((String) value);
				return;
			case GrudiModelPackage.PERSONINFO_GENDER_FEATURE_ID:
				getTarget().setGender((Gender) value);
				return;
			default:
				super.eSet(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eAddTo(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eAddTo(eStructuralFeature, value);
			}
		}

		/**
		 * @generated
		 */
		@Override
		public boolean eRemoveFrom(EStructuralFeature eStructuralFeature, Object value) {
			final int featureID = eClass().getFeatureID(eStructuralFeature);
			switch (featureID) {

			default:
				return super.eRemoveFrom(eStructuralFeature, value);
			}
		}
	}
}