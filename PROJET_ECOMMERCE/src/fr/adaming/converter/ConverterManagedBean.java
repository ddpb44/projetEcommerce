package fr.adaming.converter;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.adaming.managedBeans.CategorieMB;
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;


@FacesConverter("Converter")
public class ConverterManagedBean implements Converter {
	
	@EJB
	private ICategorieService catService;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	List<Categorie> listeCat = catService.getAllCategorie();
            	return listeCat.get(Integer.parseInt(value));
            	//return cService.getAllCategorie().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
