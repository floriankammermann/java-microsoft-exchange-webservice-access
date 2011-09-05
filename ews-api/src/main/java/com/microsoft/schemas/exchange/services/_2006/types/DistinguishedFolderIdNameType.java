
package com.microsoft.schemas.exchange.services._2006.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DistinguishedFolderIdNameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DistinguishedFolderIdNameType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="calendar"/>
 *     &lt;enumeration value="contacts"/>
 *     &lt;enumeration value="deleteditems"/>
 *     &lt;enumeration value="drafts"/>
 *     &lt;enumeration value="inbox"/>
 *     &lt;enumeration value="journal"/>
 *     &lt;enumeration value="notes"/>
 *     &lt;enumeration value="outbox"/>
 *     &lt;enumeration value="sentitems"/>
 *     &lt;enumeration value="tasks"/>
 *     &lt;enumeration value="msgfolderroot"/>
 *     &lt;enumeration value="publicfoldersroot"/>
 *     &lt;enumeration value="root"/>
 *     &lt;enumeration value="junkemail"/>
 *     &lt;enumeration value="searchfolders"/>
 *     &lt;enumeration value="voicemail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DistinguishedFolderIdNameType")
@XmlEnum
public enum DistinguishedFolderIdNameType {

    @XmlEnumValue("calendar")
    CALENDAR("calendar"),
    @XmlEnumValue("contacts")
    CONTACTS("contacts"),
    @XmlEnumValue("deleteditems")
    DELETEDITEMS("deleteditems"),
    @XmlEnumValue("drafts")
    DRAFTS("drafts"),
    @XmlEnumValue("inbox")
    INBOX("inbox"),
    @XmlEnumValue("journal")
    JOURNAL("journal"),
    @XmlEnumValue("notes")
    NOTES("notes"),
    @XmlEnumValue("outbox")
    OUTBOX("outbox"),
    @XmlEnumValue("sentitems")
    SENTITEMS("sentitems"),
    @XmlEnumValue("tasks")
    TASKS("tasks"),
    @XmlEnumValue("msgfolderroot")
    MSGFOLDERROOT("msgfolderroot"),
    @XmlEnumValue("publicfoldersroot")
    PUBLICFOLDERSROOT("publicfoldersroot"),
    @XmlEnumValue("root")
    ROOT("root"),
    @XmlEnumValue("junkemail")
    JUNKEMAIL("junkemail"),
    @XmlEnumValue("searchfolders")
    SEARCHFOLDERS("searchfolders"),
    @XmlEnumValue("voicemail")
    VOICEMAIL("voicemail");
    private final String value;

    DistinguishedFolderIdNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DistinguishedFolderIdNameType fromValue(String v) {
        for (DistinguishedFolderIdNameType c: DistinguishedFolderIdNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
