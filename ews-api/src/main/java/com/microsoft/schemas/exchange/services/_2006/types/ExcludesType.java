
package com.microsoft.schemas.exchange.services._2006.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExcludesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExcludesType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.microsoft.com/exchange/services/2006/types}SearchExpressionType">
 *       &lt;sequence>
 *         &lt;element ref="{http://schemas.microsoft.com/exchange/services/2006/types}Path"/>
 *         &lt;element name="Bitmask" type="{http://schemas.microsoft.com/exchange/services/2006/types}ExcludesValueType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExcludesType", propOrder = {
    "path",
    "bitmask"
})
public class ExcludesType
    extends SearchExpressionType
{

    @XmlElementRef(name = "Path", namespace = "http://schemas.microsoft.com/exchange/services/2006/types", type = JAXBElement.class)
    protected JAXBElement<? extends BasePathToElementType> path;
    @XmlElement(name = "Bitmask", required = true)
    protected ExcludesValueType bitmask;

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PathToExtendedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PathToUnindexedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PathToIndexedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link BasePathToElementType }{@code >}
     *     
     */
    public JAXBElement<? extends BasePathToElementType> getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PathToExtendedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PathToUnindexedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PathToIndexedFieldType }{@code >}
     *     {@link JAXBElement }{@code <}{@link BasePathToElementType }{@code >}
     *     
     */
    public void setPath(JAXBElement<? extends BasePathToElementType> value) {
        this.path = ((JAXBElement<? extends BasePathToElementType> ) value);
    }

    /**
     * Gets the value of the bitmask property.
     * 
     * @return
     *     possible object is
     *     {@link ExcludesValueType }
     *     
     */
    public ExcludesValueType getBitmask() {
        return bitmask;
    }

    /**
     * Sets the value of the bitmask property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExcludesValueType }
     *     
     */
    public void setBitmask(ExcludesValueType value) {
        this.bitmask = value;
    }

}
