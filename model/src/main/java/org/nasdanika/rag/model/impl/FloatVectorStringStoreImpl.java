/**
 */
package org.nasdanika.rag.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.rag.model.FloatVectorStringItem;
import org.nasdanika.rag.model.FloatVectorStringStore;
import org.nasdanika.rag.model.RagPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Float Vector String Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rag.model.impl.FloatVectorStringStoreImpl#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatVectorStringStoreImpl extends MinimalEObjectImpl.Container implements FloatVectorStringStore {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatVectorStringStoreImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RagPackage.Literals.FLOAT_VECTOR_STRING_STORE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<FloatVectorStringItem> getItems() {
		return (EList<FloatVectorStringItem>)eDynamicGet(RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS, RagPackage.Literals.FLOAT_VECTOR_STRING_STORE__ITEMS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS:
				return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS:
				return getItems();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends FloatVectorStringItem>)newValue);
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
			case RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS:
				getItems().clear();
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
			case RagPackage.FLOAT_VECTOR_STRING_STORE__ITEMS:
				return !getItems().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FloatVectorStringStoreImpl
