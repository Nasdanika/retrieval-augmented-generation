/**
 */
package org.nasdanika.rag.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float Vector String Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Stores float vectors identified by strrings (URI's)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rag.model.FloatVectorStringStore#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rag.model.RagPackage#getFloatVectorStringStore()
 * @model
 * @generated
 */
public interface FloatVectorStringStore extends EObject {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rag.model.FloatVectorStringItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Store items
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.rag.model.RagPackage#getFloatVectorStringStore_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<FloatVectorStringItem> getItems();

} // FloatVectorStringStore
