package pl.benzo.enzo.kmservicedto.uploader;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.Getter;
import lombok.Setter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fileBase64"
})
@XmlRootElement(name = "loadImageResponse")
@Getter
@Setter
public class LoadImageResponse {
    @XmlElement(required = true)
    protected String fileBase64;

}
