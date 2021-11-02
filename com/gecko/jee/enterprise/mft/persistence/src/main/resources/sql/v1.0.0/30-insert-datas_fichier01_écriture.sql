-- 
-- Protocole "Fichier01_écriture"
-- 
---__n.repatmt:cr_atmt fichier01_écriture
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple 0 ouverture_dmd dem_e_ouv_p_e i_chemin_fic "" ouv_p_e "e_id_fic,i_chemin_fic" "&e_id_fic&:=_auto-gen_"
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple ouverture_dmd ouvert ouv_p_e_R "" "" dem_e_ouv_p_e_R "" ""
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple ouvert écriture_ligne_dmd dem_e_écrire_ligne i_ligne "" écrire_ligne "e_id_fic,i_ligne" ""
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple écriture_ligne_dmd ouvert écrire_ligne_R "" "" dem_s_ligne_écrite "" ""
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple ouvert fermeture_dmd dem_e_fermer_e "" "" fermer_e e_id_fic ""
---_n.repatmt:ajt_tran_atmt fichier01_écriture "" TransitionSimple fermeture_dmd 0 fermer_e_R "" "" dem_s_fermé_e "" ""

-- Automate
INSERT INTO public.automate (ident, est_de_gds) VALUES ('fichier01_écriture_atmt', false);
-- Protocole
INSERT INTO public.protocole (ident, automate_id) VALUES ('fichier01_écriture', (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));

-- Etats
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatComposite', 'fichier01_écriture_ouverture_etat', '', null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatComposite', 'fichier01_écriture_écriture_etat', '', null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));

INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', '0','' , null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'ouverture_dmd', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'ouvert', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'écriture_ligne_dmd', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_écriture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'fermeture_dmd', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));


-- Fonctionnalités
INSERT INTO public.fonctionnalite (est_activée, ident, etat_composite_etat_associe_id, protocole_id) VALUES (true, 'fichier01_écriture_ouverture', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_ouverture_etat'), (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.fonctionnalite (est_activée, ident, etat_composite_etat_associe_id, protocole_id) VALUES (true, 'fichier01_écriture_écriture', (SELECT ID FROM public.etat WHERE ident = 'fichier01_écriture_écriture_etat'), (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));

-- Gardes


--protocol_signal 
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_écrire_ligne') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_e') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_e') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne_écrite') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'fermer_e') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'fermer_e_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_écriture'));

-- Transitions
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t01', null, (SELECT ID FROM public.etat WHERE ident = 'ouverture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e' ), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e'), (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t02', null, (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e_R' ), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e_R'), (SELECT ID FROM public.etat WHERE ident = 'ouverture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t03', null, (SELECT ID FROM public.etat WHERE ident = 'écriture_ligne_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne' ), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_écrire_ligne' ), (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t04', null, (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne_écrite'), (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne_R'), (SELECT ID FROM public.etat WHERE ident = 'écriture_ligne_dmd'  and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t05', null, (SELECT ID FROM public.etat WHERE ident = 'fermeture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'fermer_e'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_e' ), (SELECT ID FROM public.etat WHERE ident = 'ouvert'  and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_écriture_t06', null, (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_e'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_e_R') , (SELECT ID FROM public.etat WHERE ident = 'fermeture_dmd'  and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'))) ;


-- Affectations
INSERT INTO public.affectation (expression, parametre_variable_ex_id, transition_id) VALUES ('&e_id_fic&:=_auto-gen_', (SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.transition WHERE ident = 'fichier01_écriture_t01'));

-- Associations 
INSERT INTO public.parametre_affectation (parametre_id, affectations_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.affectation WHERE expression = '&e_id_fic&:=_auto-gen_' and transition_id =(SELECT ID FROM public.transition WHERE ident = 'fichier01_écriture_t01')));

INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_ligne'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_écrire_ligne'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_ligne'), (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_e'));

-- Connecteurs
INSERT INTO public.connecteur (dtype, ident, nom_canonique, connecteur_composite_parent_id) VALUES ('ConnecteurÉlémentaire', 'fichier01_écriture', 'fr.s3ca.sds01.outils.connecteurs.fichier01.frontal.SessionDeCommandesLocalesFichier01', null);
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_e_R'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_e'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_e'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_écrire_ligne'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e_R'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e_R'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_e'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne_R'));


INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'écrire_ligne'));
INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_e'));
INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_écriture'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne_écrite'));