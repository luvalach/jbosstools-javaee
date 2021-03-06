/******************************************************************************* 
 * Copyright (c) 2007 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.xml.beans.model;

import org.jboss.tools.common.model.XModelObject;
import org.jboss.tools.common.model.loaders.impl.SimpleWebFileLoader;
import org.jboss.tools.common.model.util.NamespaceMapping;
import org.jboss.tools.common.model.util.XModelObjectLoaderUtil;
import org.w3c.dom.Element;

public class CDIBeansFileLoader extends SimpleWebFileLoader {
    
    public CDIBeansFileLoader() {}

    protected XModelObjectLoaderUtil createUtil() {
        return new CDIBeansLoaderUtil();
    }
    
    protected boolean isCheckingDTD() {
    	return false;
    }
    
    protected boolean isCheckingSchema() {
    	return false;
    }

    protected String loadNamespace(Element element, XModelObject object) {
    	NamespaceMapping namespaceMapping = CDINamespaces.getInstance(object.getModel().getMetaData(), getVersionSuffix(object)).getNamespaceMapping(element);
    	object.set(NamespaceMapping.ATTR_NAMESPACE_MAPPING, namespaceMapping.toString()); //$NON-NLS-1$
    	util.setNamespaceMapping(namespaceMapping);
    	
    	return super.loadNamespace(element, object);
    }

    public Element createRootElement(XModelObject object) {
    	String rootName = getRootName(object);
        Element element = createRootElement(rootName, null, null);
        CDINamespaces.getInstance(object.getModel().getMetaData(), getVersionSuffix(object)).validateNamespaces(object, element);
		NamespaceMapping namespaceMapping = NamespaceMapping.load(object);
    	util.setNamespaceMapping(namespaceMapping);
    	return element;
    }

    private String getVersionSuffix(XModelObject o) {
//    	String entity = o.getModelEntity().getName();
    	return ""; //$NON-NLS-1$
    }
    
}
