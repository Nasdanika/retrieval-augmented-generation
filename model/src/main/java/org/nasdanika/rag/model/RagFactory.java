/**
 */
package org.nasdanika.rag.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.rag.model.RagPackage
 * @generated
 */
public interface RagFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RagFactory eINSTANCE = org.nasdanika.rag.model.impl.RagFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Double Vector String Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Vector String Item</em>'.
	 * @generated
	 */
	DoubleVectorStringItem createDoubleVectorStringItem();

	/**
	 * Returns a new object of class '<em>Double Vector String Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Vector String Store</em>'.
	 * @generated
	 */
	DoubleVectorStringStore createDoubleVectorStringStore();

	/**
	 * Returns a new object of class '<em>Float Vector String Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Vector String Item</em>'.
	 * @generated
	 */
	FloatVectorStringItem createFloatVectorStringItem();

	/**
	 * Returns a new object of class '<em>Float Vector String Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Vector String Store</em>'.
	 * @generated
	 */
	FloatVectorStringStore createFloatVectorStringStore();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RagPackage getRagPackage();

} //RagFactory
