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
        "fileName"
})
@XmlRootElement(name = "loadImageRequest")
@Getter
@Setter
public class LoadImageRequest {
    @XmlElement(required = true)
    protected String fileName;

}