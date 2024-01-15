/**
 */
package org.nasdanika.rag.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.ncore.impl.StringIdentityImpl;

import org.nasdanika.rag.model.DoubleVectorStringItem;
import org.nasdanika.rag.model.RagPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Double Vector String Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rag.model.impl.DoubleVectorStringItemImpl#getVector <em>Vector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoubleVectorStringItemImpl extends StringIdentityImpl implements DoubleVectorStringItem {
	/**
	 * The default value of the '{@link #getVector() <em>Vector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVector()
	 * @generated
	 * @ordered
	 */
	protected static final double VECTOR_EDEFAULT = 0.0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DoubleVectorStringItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RagPackage.Literals.DOUBLE_VECTOR_STRING_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getVector() {
		return (Double)eDynamicGet(RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR, RagPackage.Literals.DOUBLE_VECTOR_STRING_ITEM__VECTOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVector(double newVector) {
		eDynamicSet(RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR, RagPackage.Literals.DOUBLE_VECTOR_STRING_ITEM__VECTOR, newVector);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR:
				return getVector();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR:
				setVector((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR:
				setVector(VECTOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RagPackage.DOUBLE_VECTOR_STRING_ITEM__VECTOR:
				return getVector() != VECTOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //DoubleVectorStringItemImpl
