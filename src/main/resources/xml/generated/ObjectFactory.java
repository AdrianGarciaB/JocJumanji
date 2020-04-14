//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.10 at 02:00:29 PM CEST 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CardsPosition_QNAME = new QName("", "cardsPosition");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CardsPositionType }
     * 
     */
    public CardsPositionType createCardsPositionType() {
        return new CardsPositionType();
    }

    /**
     * Create an instance of {@link DeckType }
     * 
     */
    public DeckType createDeckType() {
        return new DeckType();
    }

    /**
     * Create an instance of {@link DiscardDeckType }
     * 
     */
    public DiscardDeckType createDiscardDeckType() {
        return new DiscardDeckType();
    }

    /**
     * Create an instance of {@link PlayerType }
     * 
     */
    public PlayerType createPlayerType() {
        return new PlayerType();
    }

    /**
     * Create an instance of {@link CardType }
     * 
     */
    public CardType createCardType() {
        return new CardType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardsPositionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cardsPosition")
    public JAXBElement<CardsPositionType> createCardsPosition(CardsPositionType value) {
        return new JAXBElement<CardsPositionType>(_CardsPosition_QNAME, CardsPositionType.class, null, value);
    }

}
