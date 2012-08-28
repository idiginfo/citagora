package org.idiginfo.docsvc.model.mas;

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
public enum PublicationType implements ValueEnum {

    UNKOWN("Unkown"),
    PAPER("Paper"),
    BOOK("Book"),
    POSTER("Poster");
    private final String value;

    PublicationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PublicationType fromValue(String v) {
        for (PublicationType c: PublicationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

	public static PublicationType fromNumber(int number) {
        for (PublicationType c: PublicationType.values()) {
            if (c.ordinal() == number) {
                return c;
            }
        }
		return null;
	}

}
