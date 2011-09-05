
package com.microsoft.schemas.exchange.services._2006.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NonEmptyArrayOfBaseFolderIdsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NonEmptyArrayOfBaseFolderIdsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="FolderId" type="{http://schemas.microsoft.com/exchange/services/2006/types}FolderIdType"/>
 *         &lt;element name="DistinguishedFolderId" type="{http://schemas.microsoft.com/exchange/services/2006/types}DistinguishedFolderIdType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonEmptyArrayOfBaseFolderIdsType", propOrder = {
    "folderIdOrDistinguishedFolderId"
})
public class NonEmptyArrayOfBaseFolderIdsType {

    @XmlElements({
        @XmlElement(name = "DistinguishedFolderId", type = DistinguishedFolderIdType.class),
        @XmlElement(name = "FolderId", type = FolderIdType.class)
    })
    protected List<BaseFolderIdType> folderIdOrDistinguishedFolderId;

    /**
     * Gets the value of the folderIdOrDistinguishedFolderId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the folderIdOrDistinguishedFolderId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFolderIdOrDistinguishedFolderId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistinguishedFolderIdType }
     * {@link FolderIdType }
     * 
     * 
     */
    public List<BaseFolderIdType> getFolderIdOrDistinguishedFolderId() {
        if (folderIdOrDistinguishedFolderId == null) {
            folderIdOrDistinguishedFolderId = new ArrayList<BaseFolderIdType>();
        }
        return this.folderIdOrDistinguishedFolderId;
    }

}
