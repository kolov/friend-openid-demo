package net.kolov.xsd;

import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.xs.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import static org.w3c.dom.bootstrap.DOMImplementationRegistry.newInstance;

public class SchemaParser {
    public static void main(String[] args) throws Exception {
        System.setProperty(DOMImplementationRegistry.PROPERTY, "com.sun.org.apache.xerces.internal.dom.DOMXSImplementationSourceImpl");
        DOMImplementationRegistry registry = newInstance();
        XSImplementation impl = (XSImplementation) registry.getDOMImplementation("XS-Loader");
        XSLoader schemaLoader = impl.createXSLoader(null);

        XSModel model = schemaLoader.loadURI("resources/public/s.xsd");

        XSNamedMap c1 = model.getComponents(XSConstants.ELEMENT_DECLARATION);

        for (int i = 0; i < c1.getLength(); i++) {
            XSElementDeclaration eld = (XSElementDeclaration) c1.item(i);
            System.out.println("Element Decl: " + eld.getName() + ":");
            printTerm(eld);
        }
    }

    private static void printTerm(XSTerm term) throws Exception {
        if (XSElementDeclaration.class.isAssignableFrom(term.getClass())) {

            XSElementDeclaration eld = (XSElementDeclaration) term;
            final XSTypeDefinition typeDefinition = eld.getTypeDefinition();
            if (XSComplexTypeDefinition.class.isAssignableFrom(typeDefinition.getClass())) {
                XSComplexTypeDefinition ctd = (XSComplexTypeDefinition) typeDefinition;


                System.out.println("name " + eld.getName() + ":");
                XSParticle particle = ctd.getParticle();
                XSTerm t = particle.getTerm();
                printTerm(t);
            } else {
                XSSimpleTypeDecl std = (XSSimpleTypeDecl) typeDefinition;
                std.getType();
                if (std.getNamespace().equals("http://www.w3.org/2001/XMLSchema")) {
                    System.out.println("\ttype=" + std.getTypeName());
                } else {
                    System.out.println("\ttype defrived from =" + std.getBaseType().getName());
                    XSObjectList facets = std.getFacets();
                    for (int j = 0; j < facets.getLength(); j++) {
                        XSFacet facet = (XSFacet) facets.item(j);
                        System.out.println( "\tfacet" + facet.getFacetKind() + "," + facet.getLexicalFacetValue());
                    }

                }
            }
        } else if (XSModelGroup.class.isAssignableFrom(term.getClass())) {
            XSModelGroup mgi = (XSModelGroup) term;

            XSObjectList fParticles = mgi.getParticles();
            for (int j = 0; j < fParticles.getLength(); j++) {
                XSParticleDecl pd = (XSParticleDecl) fParticles.item(j);

                final XSTerm fValue = pd.fValue;
                System.out.println("\tparticle= " + fValue + "{" + pd.getMinOccurs() + "," + pd.getMaxOccurs() + "}:");

                printTerm(fValue);
            }
        }
    }
}
