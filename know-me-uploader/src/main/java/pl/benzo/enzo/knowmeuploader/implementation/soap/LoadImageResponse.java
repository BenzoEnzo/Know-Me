package pl.benzo.enzo.knowmeuploader.implementation.soap;

import jakarta.xml.bind.annotation.*;
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
