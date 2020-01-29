/**
 * 
 */
package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController {

	@PostConstruct
	public void init() {
		Uteis.verificarLogin();
	}
}
