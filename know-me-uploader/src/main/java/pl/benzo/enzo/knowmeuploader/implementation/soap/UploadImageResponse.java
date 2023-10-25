package pl.benzo.enzo.knowmeuploader.implementation.soap;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "status"
})
@XmlRootElement(name = "uploadImageResponse")
@Getter
@Setter
public class UploadImageResponse {
    @XmlElement(required = true)
    protected String status;

}
