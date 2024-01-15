/**
 */
package org.nasdanika.rag.model;

import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Vector String Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DoubleVectorStringStore item - a mapping of String id to a double vector.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rag.model.DoubleVectorStringItem#getVector <em>Vector</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rag.model.RagPackage#getDoubleVectorStringItem()
 * @model
 * @generated
 */
public interface DoubleVectorStringItem extends StringIdentity {
	/**
	 * Returns the value of the '<em><b>Vector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Vector elements
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vector</em>' attribute.
	 * @see #setVector(double)
	 * @see org.nasdanika.rag.model.RagPackage#getDoubleVectorStringItem_Vector()
	 * @model
	 * @generated
	 */
	double getVector();

	/**
	 * Sets the value of the '{@link org.nasdanika.rag.model.DoubleVectorStringItem#getVector <em>Vector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vector</em>' attribute.
	 * @see #getVector()
	 * @generated
	 */
	void setVector(double value);

} // DoubleVectorStringItem
