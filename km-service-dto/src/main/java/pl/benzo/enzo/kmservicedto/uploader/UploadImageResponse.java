package pl.benzo.enzo.kmservicedto.uploader;

import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

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
