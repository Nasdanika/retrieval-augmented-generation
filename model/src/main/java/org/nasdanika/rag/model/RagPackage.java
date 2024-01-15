/**
 */
package org.nasdanika.rag.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.nasdanika.rag.model.RagFactory
 * @model kind="package"
 * @generated
 */
public interface RagPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ecore://nasdanika.org/rag";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.rag";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RagPackage eINSTANCE = org.nasdanika.rag.model.impl.RagPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.rag.model.impl.DoubleVectorStringItemImpl <em>Double Vector String Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.rag.model.impl.DoubleVectorStringItemImpl
	 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getDoubleVectorStringItem()
	 * @generated
	 */
	int DOUBLE_VECTOR_STRING_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_ITEM__ID = NcorePackage.STRING_IDENTITY__ID;

	/**
	 * The feature id for the '<em><b>Vector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_ITEM__VECTOR = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Vector String Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_ITEM_FEATURE_COUNT = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Vector String Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_ITEM_OPERATION_COUNT = NcorePackage.STRING_IDENTITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.rag.model.impl.DoubleVectorStringStoreImpl <em>Double Vector String Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.rag.model.impl.DoubleVectorStringStoreImpl
	 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getDoubleVectorStringStore()
	 * @generated
	 */
	int DOUBLE_VECTOR_STRING_STORE = 1;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_STORE__ITEMS = 0;

	/**
	 * The number of structural features of the '<em>Double Vector String Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_STORE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Double Vector String Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VECTOR_STRING_STORE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.rag.model.impl.FloatVectorStringItemImpl <em>Float Vector String Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.rag.model.impl.FloatVectorStringItemImpl
	 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getFloatVectorStringItem()
	 * @generated
	 */
	int FLOAT_VECTOR_STRING_ITEM = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_ITEM__ID = NcorePackage.STRING_IDENTITY__ID;

	/**
	 * The feature id for the '<em><b>Vector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_ITEM__VECTOR = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Vector String Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_ITEM_FEATURE_COUNT = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Vector String Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_ITEM_OPERATION_COUNT = NcorePackage.STRING_IDENTITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.rag.model.impl.FloatVectorStringStoreImpl <em>Float Vector String Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.rag.model.impl.FloatVectorStringStoreImpl
	 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getFloatVectorStringStore()
	 * @generated
	 */
	int FLOAT_VECTOR_STRING_STORE = 3;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_STORE__ITEMS = 0;

	/**
	 * The number of structural features of the '<em>Float Vector String Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_STORE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Float Vector String Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VECTOR_STRING_STORE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.rag.model.DoubleVectorStringItem <em>Double Vector String Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Vector String Item</em>'.
	 * @see org.nasdanika.rag.model.DoubleVectorStringItem
	 * @generated
	 */
	EClass getDoubleVectorStringItem();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.rag.model.DoubleVectorStringItem#getVector <em>Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vector</em>'.
	 * @see org.nasdanika.rag.model.DoubleVectorStringItem#getVector()
	 * @see #getDoubleVectorStringItem()
	 * @generated
	 */
	EAttribute getDoubleVectorStringItem_Vector();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.rag.model.DoubleVectorStringStore <em>Double Vector String Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Vector String Store</em>'.
	 * @see org.nasdanika.rag.model.DoubleVectorStringStore
	 * @generated
	 */
	EClass getDoubleVectorStringStore();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.rag.model.DoubleVectorStringStore#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see org.nasdanika.rag.model.DoubleVectorStringStore#getItems()
	 * @see #getDoubleVectorStringStore()
	 * @generated
	 */
	EReference getDoubleVectorStringStore_Items();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.rag.model.FloatVectorStringItem <em>Float Vector String Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Vector String Item</em>'.
	 * @see org.nasdanika.rag.model.FloatVectorStringItem
	 * @generated
	 */
	EClass getFloatVectorStringItem();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.rag.model.FloatVectorStringItem#getVector <em>Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vector</em>'.
	 * @see org.nasdanika.rag.model.FloatVectorStringItem#getVector()
	 * @see #getFloatVectorStringItem()
	 * @generated
	 */
	EAttribute getFloatVectorStringItem_Vector();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.rag.model.FloatVectorStringStore <em>Float Vector String Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Vector String Store</em>'.
	 * @see org.nasdanika.rag.model.FloatVectorStringStore
	 * @generated
	 */
	EClass getFloatVectorStringStore();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.rag.model.FloatVectorStringStore#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see org.nasdanika.rag.model.FloatVectorStringStore#getItems()
	 * @see #getFloatVectorStringStore()
	 * @generated
	 */
	EReference getFloatVectorStringStore_Items();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RagFactory getRagFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.rag.model.impl.DoubleVectorStringItemImpl <em>Double Vector String Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.rag.model.impl.DoubleVectorStringItemImpl
		 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getDoubleVectorStringItem()
		 * @generated
		 */
		EClass DOUBLE_VECTOR_STRING_ITEM = eINSTANCE.getDoubleVectorStringItem();

		/**
		 * The meta object literal for the '<em><b>Vector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_VECTOR_STRING_ITEM__VECTOR = eINSTANCE.getDoubleVectorStringItem_Vector();

		/**
		 * The meta object literal for the '{@link org.nasdanika.rag.model.impl.DoubleVectorStringStoreImpl <em>Double Vector String Store</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.rag.model.impl.DoubleVectorStringStoreImpl
		 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getDoubleVectorStringStore()
		 * @generated
		 */
		EClass DOUBLE_VECTOR_STRING_STORE = eINSTANCE.getDoubleVectorStringStore();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOUBLE_VECTOR_STRING_STORE__ITEMS = eINSTANCE.getDoubleVectorStringStore_Items();

		/**
		 * The meta object literal for the '{@link org.nasdanika.rag.model.impl.FloatVectorStringItemImpl <em>Float Vector String Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.rag.model.impl.FloatVectorStringItemImpl
		 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getFloatVectorStringItem()
		 * @generated
		 */
		EClass FLOAT_VECTOR_STRING_ITEM = eINSTANCE.getFloatVectorStringItem();

		/**
		 * The meta object literal for the '<em><b>Vector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_VECTOR_STRING_ITEM__VECTOR = eINSTANCE.getFloatVectorStringItem_Vector();

		/**
		 * The meta object literal for the '{@link org.nasdanika.rag.model.impl.FloatVectorStringStoreImpl <em>Float Vector String Store</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.rag.model.impl.FloatVectorStringStoreImpl
		 * @see org.nasdanika.rag.model.impl.RagPackageImpl#getFloatVectorStringStore()
		 * @generated
		 */
		EClass FLOAT_VECTOR_STRING_STORE = eINSTANCE.getFloatVectorStringStore();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOAT_VECTOR_STRING_STORE__ITEMS = eINSTANCE.getFloatVectorStringStore_Items();

	}

} //RagPackage
