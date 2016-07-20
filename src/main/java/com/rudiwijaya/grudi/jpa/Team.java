package com.rudiwijaya.grudi.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * A representation of the model object '<em><b>Team</b></em>'. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@Entity(name = "Team")
public class Team implements Serializable {
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
	private String name = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "team", orphanRemoval = true)
	private final List<TeamLine> lines = new ArrayList<TeamLine>();

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
	 * Sets the '{@link Team#getId() <em>id</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newId
	 *            the new value of the '{@link Team#getId() id}' feature.
	 * @generated
	 */
	public void setId(Long newId) {
		id = newId;
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
	 * Sets the '{@link Team#getName() <em>name</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newName
	 *            the new value of the '{@link Team#getName() name}' feature.
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Returns the value of '<em><b>lines</b></em>' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of '<em><b>lines</b></em>' feature
	 * @generated
	 */
	public List<TeamLine> getLines() {
		return lines;
	}

	/**
	 * Adds to the <em>lines</em> feature.
	 *
	 * @param linesValue
	 *            the value to add
	 * @return true if the value is added to the collection (it was not yet
	 *         present in the collection), false otherwise
	 * @generated
	 */
	public boolean addToLines(TeamLine linesValue) {
		if (!lines.contains(linesValue)) {
			boolean result = lines.add(linesValue);
			linesValue.setTeam(this);
			return result;
		}
		return false;
	}

	/**
	 * Removes from the <em>lines</em> feature.
	 *
	 * @param linesValue
	 *            the value to remove
	 * @return true if the value is removed from the collection (it existed in
	 *         the collection before removing), false otherwise
	 *
	 * @generated
	 */
	public boolean removeFromLines(TeamLine linesValue) {
		if (lines.contains(linesValue)) {
			boolean result = lines.remove(linesValue);
			linesValue.setTeam(null);
			return result;
		}
		return false;
	}

	/**
	 * Clears the <em>lines</em> feature.
	 * 
	 * @generated
	 */
	public void clearLines() {
		while (!lines.isEmpty()) {
			removeFromLines(lines.iterator().next());
		}
	}

	/**
	 * Sets the '{@link Team#getLines() <em>lines</em>}' feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newLines
	 *            the new value of the '{@link Team#getLines() lines}' feature.
	 * @generated
	 */
	public void setLines(List<TeamLine> newLines) {
		clearLines();
		for (TeamLine value : newLines) {
			addToLines(value);
		}
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
	 * Sets the '{@link Team#getVersionNumber() <em>versionNumber</em>}'
	 * feature.
	 *
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param newVersionNumber
	 *            the new value of the '{@link Team#getVersionNumber()
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
		return "Team " + " [id: " + getId() + "]" + " [name: " + getName() + "]" + " [versionNumber: "
				+ getVersionNumber() + "]";
	}
}
