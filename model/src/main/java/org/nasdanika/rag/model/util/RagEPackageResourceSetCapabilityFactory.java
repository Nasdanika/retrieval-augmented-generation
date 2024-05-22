package org.nasdanika.rag.model.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.capability.emf.EPackageCapabilityFactory;
import org.nasdanika.rag.model.RagPackage;

public class RagEPackageResourceSetCapabilityFactory extends EPackageCapabilityFactory {

	@Override
	protected EPackage getEPackage() {
		return RagPackage.eINSTANCE;
	}

	@Override
	protected URI getDocumentationURI() {
		return null; // URI.createURI("https://rag.models.nasdanika.org/");
	}

}
