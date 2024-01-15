/**
 */
package org.nasdanika.rag.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.rag.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RagFactoryImpl extends EFactoryImpl implements RagFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RagFactory init() {
		try {
			RagFactory theRagFactory = (RagFactory)EPackage.Registry.INSTANCE.getEFactory(RagPackage.eNS_URI);
			if (theRagFactory != null) {
				return theRagFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RagFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RagFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RagPackage.DOUBLE_VECTOR_STRING_ITEM: return createDoubleVectorStringItem();
			case RagPackage.DOUBLE_VECTOR_STRING_STORE: return createDoubleVectorStringStore();
			case RagPackage.FLOAT_VECTOR_STRING_ITEM: return createFloatVectorStringItem();
			case RagPackage.FLOAT_VECTOR_STRING_STORE: return createFloatVectorStringStore();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleVectorStringItem createDoubleVectorStringItem() {
		DoubleVectorStringItemImpl doubleVectorStringItem = new DoubleVectorStringItemImpl();
		return doubleVectorStringItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleVectorStringStore createDoubleVectorStringStore() {
		DoubleVectorStringStoreImpl doubleVectorStringStore = new DoubleVectorStringStoreImpl();
		return doubleVectorStringStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatVectorStringItem createFloatVectorStringItem() {
		FloatVectorStringItemImpl floatVectorStringItem = new FloatVectorStringItemImpl();
		return floatVectorStringItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatVectorStringStore createFloatVectorStringStore() {
		FloatVectorStringStoreImpl floatVectorStringStore = new FloatVectorStringStoreImpl();
		return floatVectorStringStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RagPackage getRagPackage() {
		return (RagPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RagPackage getPackage() {
		return RagPackage.eINSTANCE;
	}

} //RagFactoryImpl
