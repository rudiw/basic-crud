package com.rudiwijaya.grudi.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * A representation of the model object '<em><b>Person</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "Person")
public class Person implements Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	@Column(nullable = false, unique = true)
	private String username = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	@Column(nullable = false, unique = true)
	private String email = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	@Column(nullable = false)
	private String password = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	@Column(nullable = false)
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Basic()
	private String address = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Version()
	private long versionNumber = 0;

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
	 * Sets the '{@link Person#getId() <em>id</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newId
	 *            the new value of the '{@link Person#getId() id}' feature.
	 * @generated
	 */
	public void setId(Long newId) {
		id = newId;
	}

	/**
	 * Returns the value of '<em><b>username</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>username</b></em>' feature
	 * @generated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the '{@link Person#getUsername() <em>username</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newUsername
	 *            the new value of the '{@link Person#getUsername() username}'
	 *            feature.
	 * @generated
	 */
	public void setUsername(String newUsername) {
		username = newUsername;
	}

	/**
	 * Returns the value of '<em><b>email</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>email</b></em>' feature
	 * @generated
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the '{@link Person#getEmail() <em>email</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newEmail
	 *            the new value of the '{@link Person#getEmail() email}'
	 *            feature.
	 * @generated
	 */
	public void setEmail(String newEmail) {
		email = newEmail;
	}

	/**
	 * Returns the value of '<em><b>password</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>password</b></em>' feature
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the '{@link Person#getPassword() <em>password</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPassword
	 *            the new value of the '{@link Person#getPassword() password}'
	 *            feature.
	 * @generated
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
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
	 * Sets the '{@link Person#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link Person#getName() name}' feature.
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
	 * Sets the '{@link Person#getPhoneNumber() <em>phoneNumber</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newPhoneNumber
	 *            the new value of the '{@link Person#getPhoneNumber()
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
	 * Sets the '{@link Person#getGender() <em>gender</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newGender
	 *            the new value of the '{@link Person#getGender() gender}'
	 *            feature.
	 * @generated
	 */
	public void setGender(Gender newGender) {
		gender = newGender;
	}

	/**
	 * Returns the value of '<em><b>address</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>address</b></em>' feature
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the '{@link Person#getAddress() <em>address</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newAddress
	 *            the new value of the '{@link Person#getAddress() address}'
	 *            feature.
	 * @generated
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	/**
	 * Returns the value of '<em><b>versionNumber</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>versionNumber</b></em>' feature
	 * @generated
	 */
	public long getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Sets the '{@link Person#getVersionNumber() <em>versionNumber</em>}'
	 * feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newVersionNumber
	 *            the new value of the '{@link Person#getVersionNumber()
	 *            versionNumber}' feature.
	 * @generated
	 */
	public void setVersionNumber(long newVersionNumber) {
		versionNumber = newVersionNumber;
	}

	/**
	 * A toString method which prints the values of all EAttributes of this
	 * instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return "Person " + " [id: " + getId() + "]" + " [username: " + getUsername() + "]" + " [email: " + getEmail()
				+ "]" + " [password: " + getPassword() + "]" + " [name: " + getName() + "]" + " [phoneNumber: "
				+ getPhoneNumber() + "]" + " [gender: " + getGender() + "]" + " [address: " + getAddress() + "]"
				+ " [versionNumber: " + getVersionNumber() + "]";
	}
}
