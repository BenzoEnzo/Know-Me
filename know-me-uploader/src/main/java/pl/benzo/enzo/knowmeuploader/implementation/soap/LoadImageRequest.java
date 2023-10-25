package pl.benzo.enzo.knowmeuploader.implementation.soap;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
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