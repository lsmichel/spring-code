package com.gecko.jee.enterprise.mft.business.service;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gecko.jee.enterprise.mft.business.model.StructCommandeNoyau;
import com.gecko.jee.enterprise.mft.business.model.StructRetourCommandeNoyau;
import com.gecko.jee.enterprise.mft.exception.TechnicalException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <b>Description: Implémentation du service d'accès au noyau. Il s'agit d'une
 * implémentation d'un client REST pour les appels au noyau MFT X-Protocols.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */

public class NoyauServiceRest extends AbstractService implements NoyauService {

	/**
	 * Outil de conversion POJO <-> JSON
	 */
	private final Gson gson = new Gson();

	/**
	 * Media type pour envoyer du JSON dans le body
	 */
	private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(NoyauService.class);

	/**
	 * Instancié une seule fois car il gère le multithreading.
	 */
	private final OkHttpClient oKHttpClient = new OkHttpClient();

	/**
	 * {@inheritDoc}. Appel REST en mode GET.
	 */
	@Override
	public String creerSessionDeCommandes(final Object objectToTransform) {
		// construction de l'url
		final StringBuilder url = new StringBuilder().append(this.getNoyauServiceRestBaseUrl()).append("/")
				.append(this.businessConfigProperties.getProperty("noyau.services.rest.path.creerSessionDeCommandes"));
		// On passe l'objet dans l'URL
		if (objectToTransform != null) {
			url.append("/");
			url.append(objectToTransform.toString());
		} else {
			url.append("/null");
		}

		// construction de la request REST
		final Request request = new Request.Builder().url(url.toString()).get().build();
		// fin construction requette
		Response response = null;
		try {
			// appel au server
			response = this.oKHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				// sinon erreur à gérer, à voir avec Olivier
				// TODO
				this.logger.info("erreur call api === respone : " + response.body().string());

				throw new TechnicalException(
						this.businessExceptionProperties
								.getProperty("technical.exception.creerSessionDeCommandes.response.code"),
						this.businessExceptionProperties.getProperty(
								"technical.exception.creerSessionDeCommandes.response.message"),
						response.toString());
			}
			// On récupère l'id de session créé
			final String creerSessionDeCommandesResponse = response.body().string();
			return creerSessionDeCommandesResponse;
		} catch (final IOException e) {
			throw new TechnicalException(
					this.businessExceptionProperties.getProperty("technical.exception.creerSessionDeCommandes.io.code"),
					this.businessExceptionProperties
							.getProperty("technical.exception.creerSessionDeCommandes.io.message"),
					url);

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void demarrerConnecteurs() {
		// TODO Auto-generated method stub

	}

	/**
	 * Construction de l'URL du service REST du noyau.
	 *
	 * @return l'URL du service REST du noyau.
	 */
	private String getNoyauServiceRestBaseUrl() {
		return "http://" + this.businessConfigProperties.getProperty("noyau.services.rest.host") + ":"
				+ this.businessConfigProperties.getProperty("noyau.services.rest.port") + "/"
				+ this.businessConfigProperties.getProperty("noyau.services.rest.basePath");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerSessionDeCommandes(final String identifiant) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StructRetourCommandeNoyau traiterCommandeSurSessionDeCommande(final String identifiantDeSessionDeCommandes,
			final StructCommandeNoyau structureCommandeNoyau) {

		StructRetourCommandeNoyau structRetourCommandeNoyauResponse = null;

		// Construction de l'url
		final String url = this.getNoyauServiceRestBaseUrl() + "/"
				+ this.businessConfigProperties
						.getProperty("noyau.services.rest.path.traiterCommandeSurSessionDeCommande")
				+ "/" + identifiantDeSessionDeCommandes;
		final String stringJsonStructureCommandeNoyau = this.gson.toJson(structureCommandeNoyau);
		this.logger.info("commande envoyée ======>   " + stringJsonStructureCommandeNoyau);
		// construction de la request REST
		final RequestBody body = RequestBody.create(this.JSON, stringJsonStructureCommandeNoyau);
		final Request request = new Request.Builder().url(url).post(body).build();
		Response response = null;
		try {
			// appel au server
			response = this.oKHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				// sinon erreur à gérer, à voir avec Olivier
				// TODO
				this.logger.info("erreur call api === respone : " + response.body().string());
				throw new TechnicalException(
						this.businessExceptionProperties
								.getProperty("technical.exception.traiterCommandeSurSessionDeCommande.response.code"),
						this.businessExceptionProperties.getProperty(
								"technical.exception.traiterCommandeSurSessionDeCommande.response.message"),
						identifiantDeSessionDeCommandes, structureCommandeNoyau);

			}
			// si la reponse est ok
			// transformation du response Body en pojo StructRetourCommandeNoyau
			final Type type = new TypeToken<StructRetourCommandeNoyau>() {
			}.getType();
			structRetourCommandeNoyauResponse = this.gson.fromJson(response.body().string(), type);
		} catch (final IOException e) {
			throw new TechnicalException(
					this.businessExceptionProperties
							.getProperty("technical.exception.traiterCommandeSurSessionDeCommande.io.code"),
					this.businessExceptionProperties
							.getProperty("technical.exception.traiterCommandeSurSessionDeCommande.io.message"),
					identifiantDeSessionDeCommandes, structureCommandeNoyau);

		}
		return structRetourCommandeNoyauResponse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StructRetourCommandeNoyau traiterLigneSurSessionDeCommande(final String identifiantDeSessionDeCommandes,
			final String ligne) {
		// TODO Auto-generated method stub
		return null;
	}

}
