package com.rudiwijaya.grudi.jpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * A representation of the model object '<em><b>PersonInfo</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Embeddable()
public class PersonInfo implements Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1L;
	
	public void copyFrom(Person upPerson) {
		setGender(upPerson.getGender());
		setId(upPerson.getId());
		setName(upPerson.getName());
		setPhoneNumber(upPerson.getPhoneNumber());
		setUserName(upPerson.getUsername());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	private Long id = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	private String userName = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	private String name = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	private String phoneNumber = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Basic()
	@Type(type = "org.jadira.usertype.corejava.PersistentEnumAsPostgreSQLEnum", parameters = @Parameter(name = "enumClass", value = "com.rudiwijaya.grudi.jpa.Gender"))
	private Gender gender = null;

	/**
	 * Returns the value of '<em><b>id</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>id</b></em>' feature
	 * @generated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the '{@link PersonInfo#getId() <em>id</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newId
	 *            the new value of the '{@link PersonInfo#getId() id}' feature.
	 * @generated
	 */
	public void setId(Long newId) {
		id = newId;
	}

	/**
	 * Returns the value of '<em><b>userName</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>userName</b></em>' feature
	 * @generated
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the '{@link PersonInfo#getUserName() <em>userName</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newUserName
	 *            the new value of the '{@link PersonInfo#getUserName()
	 *            userName}' feature.
	 * @generated
	 */
	public void setUserName(String newUserName) {
		userName = newUserName;
	}

	/**
	 * Returns the value of '<em><b>name</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>name</b></em>' feature
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the '{@link PersonInfo#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link PersonInfo#getName() name}'
	 *            feature.
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Returns the value of '<em><b>phoneNumber</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>phoneNumber</b></em>' feature
	 * @generated
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the '{@link PersonInfo#getPhoneNumber() <em>phoneNumber</em>}'
	 * feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPhoneNumber
	 *            the new value of the '{@link PersonInfo#getPhoneNumber()
	 *            phoneNumber}' feature.
	 * @generated
	 */
	public void setPhoneNumber(String newPhoneNumber) {
		phoneNumber = newPhoneNumber;
	}

	/**
	 * Returns the value of '<em><b>gender</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>gender</b></em>' feature
	 * @generated
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the '{@link PersonInfo#getGender() <em>gender</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newGender
	 *            the new value of the '{@link PersonInfo#getGender() gender}'
	 *            feature.
	 * @generated
	 */
	public void setGender(Gender newGender) {
		gender = newGender;
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "PersonInfo " + " [id: " + getId() + "]" + " [userName: " + getUserName() + "]" + " [name: " + getName()
				+ "]" + " [phoneNumber: " + getPhoneNumber() + "]" + " [gender: " + getGender() + "]";
	}
}
