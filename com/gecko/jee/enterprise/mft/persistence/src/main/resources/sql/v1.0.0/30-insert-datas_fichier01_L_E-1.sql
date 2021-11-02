-- 
-- Protocole "S_fichier01_L_E"
-- Automate de synchronisation entre fichier01_lecture et fichier01_écriture
-- 

-- _n.repatmt:cr_atmt S_fichier01_L_E
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto 0 dem_ouv_p_l_émise "" "" "" dem_e_ouv_p_l i_chemin_fic ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto 0 dem_ouv_p_e_émise "" "" "" dem_e_ouv_p_e i_chemin_fic ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple dem_ouv_p_l_émise source_ouverte dem_e_ouv_p_l_R "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple dem_ouv_p_e_émise cible_ouverte dem_e_ouv_p_e_R "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionRdv source_ouverte,cible_ouverte source_et_cible_ouvertes "" "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto source_et_cible_ouvertes en_lecture "" "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto source_et_cible_ouvertes en_écriture "" "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto en_lecture dem_lire_ligne_émise "" "" "_FdF_EstPleine_=_faux_" dem_e_lire_ligne "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionAuto en_écriture dem_écrire_ligne_émise "" "" "_FdF_EstVide_=_faux_" dem_e_écrire_ligne "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple dem_lire_ligne_émise en_lecture dem_s_ligne i_ligne "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple dem_écrire_ligne_émise en_écriture dem_s_ligne_écrite "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple dem_lire_ligne_émise fermeture_source_demandée dem_s_fin_fichier "" "" dem_e_fermer_l "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple fermeture_source_demandée source_fermée dem_s_fermé_l "" "" "" "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionRdv source_fermée,en_écriture fermeture_cible_demandée "" "" "_FdF_EstVide_=_vrai_" dem_e_fermer_e "" ""
-- _n.repatmt:ajt_tran_atmt S_fichier01_L_E "" TransitionSimple fermeture_cible_demandée 0 dem_s_fermé_e "" "" "" "" ""

-- Automate
INSERT INTO public.automate (ident, est_de_gds) VALUES ('S_fichier01_L_E_atmt', true);
-- Protocole
INSERT INTO public.protocole (ident, automate_id) VALUES ('S_fichier01_L_E', (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));

-- Fonctionnalités

-- Etats
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', '0', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'dem_ouv_p_l_émise', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'dem_ouv_p_e_émise', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'source_ouverte', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'cible_ouverte', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'source_et_cible_ouvertes', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'en_lecture', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'en_écriture', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'dem_lire_ligne_émise', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'dem_écrire_ligne_émise', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'fermeture_source_demandée', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'source_fermée', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'fermeture_cible_demandée', '', null, (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt'));


-- Gardes
INSERT INTO public.garde (expression) VALUES ('_FdF_EstPleine_=_faux_');
INSERT INTO public.garde (expression) VALUES ('_FdF_EstVide_=_faux_');
INSERT INTO public.garde (expression) VALUES ('_FdF_EstVide_=_vrai_');

-- Transitions
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t01', null, (SELECT ID FROM public.etat WHERE ident = 'dem_ouv_p_l_émise'), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l'), null, (SELECT ID FROM public.etat WHERE ident = '0'and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t02', null, (SELECT ID FROM public.etat WHERE ident = 'dem_ouv_p_e_émise'), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e'), null, (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t03', null, (SELECT ID FROM public.etat WHERE ident = 'source_ouverte'), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l_R'), (SELECT ID FROM public.etat WHERE ident = 'dem_ouv_p_l_émise' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t04', null, (SELECT ID FROM public.etat WHERE ident = 'cible_ouverte'), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e_R'), (SELECT ID FROM public.etat WHERE ident = 'dem_ouv_p_e_émise' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionRdv', 'S_fichier01_L_E_t05', null, (SELECT ID FROM public.etat WHERE ident = 'source_et_cible_ouvertes'), null, null, null, null);
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t06', null, (SELECT ID FROM public.etat WHERE ident = 'en_lecture'), null, null, null, (SELECT ID FROM public.etat WHERE ident = 'source_et_cible_ouvertes' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t07', null, (SELECT ID FROM public.etat WHERE ident = 'en_écriture'), null, null, null, (SELECT ID FROM public.etat WHERE ident = 'source_et_cible_ouvertes' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t08', null, (SELECT ID FROM public.etat WHERE ident = 'dem_lire_ligne_émise'), (SELECT ID FROM public.garde WHERE expression = '_FdF_EstPleine_=_faux_'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_lire_ligne'), null, (SELECT ID FROM public.etat WHERE ident = 'en_lecture' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionAuto', 'S_fichier01_L_E_t09', null, (SELECT ID FROM public.etat WHERE ident = 'dem_écrire_ligne_émise'), (SELECT ID FROM public.garde WHERE expression = '_FdF_EstVide_=_faux_'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_écrire_ligne'), null, (SELECT ID FROM public.etat WHERE ident = 'en_écriture' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t10', null, (SELECT ID FROM public.etat WHERE ident = 'en_lecture'), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne'), (SELECT ID FROM public.etat WHERE ident = 'dem_lire_ligne_émise' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t11', null, (SELECT ID FROM public.etat WHERE ident = 'en_écriture'), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne_écrite'), (SELECT ID FROM public.etat WHERE ident = 'dem_écrire_ligne_émise' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t12', null, (SELECT ID FROM public.etat WHERE ident = 'fermeture_source_demandée'), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_l'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fin_fichier'), (SELECT ID FROM public.etat WHERE ident = 'dem_lire_ligne_émise' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t13', null, (SELECT ID FROM public.etat WHERE ident = 'source_fermée'), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_l'), (SELECT ID FROM public.etat WHERE ident = 'fermeture_source_demandée' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionRdv', 'S_fichier01_L_E_t14', null, (SELECT ID FROM public.etat WHERE ident = 'fermeture_cible_demandée'), (SELECT ID FROM public.garde WHERE expression = '_FdF_EstVide_=_vrai_'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_e'), null, null);
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'S_fichier01_L_E_t15', null, (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')), null, null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_e'), (SELECT ID FROM public.etat WHERE ident = 'fermeture_cible_demandée' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')));

INSERT INTO public.etat_transition_rdv (etat_elementaire_id, transition_rdv_id) VALUES ((SELECT ID FROM public.etat WHERE ident = 'source_ouverte' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')), (SELECT ID FROM public.transition WHERE ident = 'S_fichier01_L_E_t05'));
INSERT INTO public.etat_transition_rdv (etat_elementaire_id, transition_rdv_id) VALUES ((SELECT ID FROM public.etat WHERE ident = 'cible_ouverte' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')), (SELECT ID FROM public.transition WHERE ident = 'S_fichier01_L_E_t05'));

INSERT INTO public.etat_transition_rdv (etat_elementaire_id, transition_rdv_id) VALUES ((SELECT ID FROM public.etat WHERE ident = 'source_fermée' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')), (SELECT ID FROM public.transition WHERE ident = 'S_fichier01_L_E_t14'));
INSERT INTO public.etat_transition_rdv (etat_elementaire_id, transition_rdv_id) VALUES ((SELECT ID FROM public.etat WHERE ident = 'en_écriture' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'S_fichier01_L_E_atmt')), (SELECT ID FROM public.transition WHERE ident = 'S_fichier01_L_E_t14'));

 
-- Affectations

-- Associations
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_ligne' ), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic' ), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_e'));

-- Connecteurs