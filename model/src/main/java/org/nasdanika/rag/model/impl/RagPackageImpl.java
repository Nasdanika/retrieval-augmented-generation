/**
 */
package org.nasdanika.rag.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.ncore.NcorePackage;

import org.nasdanika.rag.model.DoubleVectorStringItem;
import org.nasdanika.rag.model.DoubleVectorStringStore;
import org.nasdanika.rag.model.FloatVectorStringItem;
import org.nasdanika.rag.model.FloatVectorStringStore;
import org.nasdanika.rag.model.RagFactory;
import org.nasdanika.rag.model.RagPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RagPackageImpl extends EPackageImpl implements RagPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleVectorStringItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleVectorStringStoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatVectorStringItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatVectorStringStoreEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.rag.model.RagPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RagPackageImpl() {
		super(eNS_URI, RagFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link RagPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RagPackage init() {
		if (isInited) return (RagPackage)EPackage.Registry.INSTANCE.getEPackage(RagPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredRagPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		RagPackageImpl theRagPackage = registeredRagPackage instanceof RagPackageImpl ? (RagPackageImpl)registeredRagPackage : new RagPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRagPackage.createPackageContents();

		// Initialize created meta-data
		theRagPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRagPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RagPackage.eNS_URI, theRagPackage);
		return theRagPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoubleVectorStringItem() {
		return doubleVectorStringItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoubleVectorStringItem_Vector() {
		return (EAttribute)doubleVectorStringItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoubleVectorStringStore() {
		return doubleVectorStringStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoubleVectorStringStore_Items() {
		return (EReference)doubleVectorStringStoreEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatVectorStringItem() {
		return floatVectorStringItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatVectorStringItem_Vector() {
		return (EAttribute)floatVectorStringItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatVectorStringStore() {
		return floatVectorStringStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFloatVectorStringStore_Items() {
		return (EReference)floatVectorStringStoreEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RagFactory getRagFactory() {
		return (RagFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		doubleVectorStringItemEClass = createEClass(DOUBLE_VECTOR_STRING_ITEM);
		createEAttribute(doubleVectorStringItemEClass, DOUBLE_VECTOR_STRING_ITEM__VECTOR);

		doubleVectorStringStoreEClass = createEClass(DOUBLE_VECTOR_STRING_STORE);
		createEReference(doubleVectorStringStoreEClass, DOUBLE_VECTOR_STRING_STORE__ITEMS);

		floatVectorStringItemEClass = createEClass(FLOAT_VECTOR_STRING_ITEM);
		createEAttribute(floatVectorStringItemEClass, FLOAT_VECTOR_STRING_ITEM__VECTOR);

		floatVectorStringStoreEClass = createEClass(FLOAT_VECTOR_STRING_STORE);
		createEReference(floatVectorStringStoreEClass, FLOAT_VECTOR_STRING_STORE__ITEMS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		doubleVectorStringItemEClass.getESuperTypes().add(theNcorePackage.getStringIdentity());
		floatVectorStringItemEClass.getESuperTypes().add(theNcorePackage.getStringIdentity());

		// Initialize classes, features, and operations; add parameters
		initEClass(doubleVectorStringItemEClass, DoubleVectorStringItem.class, "DoubleVectorStringItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleVectorStringItem_Vector(), ecorePackage.getEDouble(), "vector", null, 0, 1, DoubleVectorStringItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleVectorStringStoreEClass, DoubleVectorStringStore.class, "DoubleVectorStringStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoubleVectorStringStore_Items(), this.getDoubleVectorStringItem(), null, "items", null, 0, -1, DoubleVectorStringStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatVectorStringItemEClass, FloatVectorStringItem.class, "FloatVectorStringItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatVectorStringItem_Vector(), ecorePackage.getEFloat(), "vector", null, 0, 1, FloatVectorStringItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatVectorStringStoreEClass, FloatVectorStringStore.class, "FloatVectorStringStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFloatVectorStringStore_Items(), this.getFloatVectorStringItem(), null, "items", null, 0, -1, FloatVectorStringStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (doubleVectorStringItemEClass,
		   source,
		   new String[] {
			   "documentation", "DoubleVectorStringStore item - a mapping of String id to a double vector."
		   });
		addAnnotation
		  (getDoubleVectorStringItem_Vector(),
		   source,
		   new String[] {
			   "documentation", "Vector elements"
		   });
		addAnnotation
		  (doubleVectorStringStoreEClass,
		   source,
		   new String[] {
			   "documentation", "Stores double vectors identified by strrings (URI\'s)"
		   });
		addAnnotation
		  (getDoubleVectorStringStore_Items(),
		   source,
		   new String[] {
			   "documentation", "Store items"
		   });
		addAnnotation
		  (floatVectorStringItemEClass,
		   source,
		   new String[] {
			   "documentation", "FloatVectorStringStore item - a mapping of String id to a float vector."
		   });
		addAnnotation
		  (getFloatVectorStringItem_Vector(),
		   source,
		   new String[] {
			   "documentation", "Vector elements"
		   });
		addAnnotation
		  (floatVectorStringStoreEClass,
		   source,
		   new String[] {
			   "documentation", "Stores float vectors identified by strrings (URI\'s)"
		   });
		addAnnotation
		  (getFloatVectorStringStore_Items(),
		   source,
		   new String[] {
			   "documentation", "Store items"
		   });
	}

} //RagPackageImpl
