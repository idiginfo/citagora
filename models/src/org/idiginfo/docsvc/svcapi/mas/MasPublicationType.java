package org.idiginfo.docsvc.svcapi.mas;

/**
 * <p>Java class for PublicationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PublicationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unkown"/>
 *     &lt;enumeration value="Paper"/>
 *     &lt;enumeration value="Book"/>
 *     &lt;enumeration value="Poster"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
public enum MasPublicationType implements ValueEnum {

    UNKOWN("Unkown"),
    PAPER("Paper"),
    BOOK("Book"),
    POSTER("Poster");
    private final String value;

    MasPublicationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MasPublicationType fromValue(String v) {
        for (MasPublicationType c: MasPublicationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

	public static MasPublicationType fromNumber(int number) {
        for (MasPublicationType c: MasPublicationType.values()) {
            if (c.ordinal() == number) {
                return c;
            }
        }
		return null;
	}

}
