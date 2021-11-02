-- 
-- Protocole "Fichier01_Lecture"
-- 
-- _n.repatmt:cr_atmt fichier01_lecture
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple 0 ouverture_dmd dem_e_ouv_p_l i_chemin_fic "" ouv_p_l "e_id_fic,i_chemin_fic" "&e_id_fic&:=_auto-gen_"
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple ouverture_dmd ouvert ouv_p_l_R "" "" dem_e_ouv_p_l_R "" ""
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple ouvert lecture_ligne_dmd dem_e_lire_ligne "" "" lire_ligne e_id_fic ""
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple lecture_ligne_dmd ouvert lire_ligne_R "i_fin_de_fic,i_ligne" "(&i_fin_de_fic&==_faux_)" dem_s_ligne i_ligne ""
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple lecture_ligne_dmd ouvert lire_ligne_R "i_fin_de_fic,i_ligne" "(&i_fin_de_fic&==_vrai_)" dem_s_fin_fichier "" ""
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple ouvert fermeture_dmd dem_e_fermer_l "" "" fermer_l "e_id_fic" ""
-- _n.repatmt:ajt_tran_atmt fichier01_lecture "" TransitionSimple fermeture_dmd 0 fermer_l_R "" "" dem_s_fermé_l "" ""


-- Automate
INSERT INTO public.automate (ident, est_de_gds) VALUES ('fichier01_lecture_atmt', false);
-- Protocole
INSERT INTO public.protocole (ident, automate_id) VALUES ('fichier01_lecture', (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));



-- Etats
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatComposite', 'fichier01_lecture_ouverture_etat', '', null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));
INSERT INTO public.etat (dtype, ident,  dER_Chemin , etat_composite_parent_id, automate_id) VALUES ('ÉtatComposite', 'fichier01_lecture_lecture_etat', '', null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_écriture_atmt'));

INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', '0', '', null, (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'ouverture_dmd', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'ouvert', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'lecture_ligne_dmd', '',  (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_lecture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));
INSERT INTO public.etat (dtype, ident, dER_Chemin, etat_composite_parent_id, automate_id) VALUES ('ÉtatÉlémentaire', 'fermeture_dmd', '', (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_ouverture_etat'), (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt'));

-- Fonctionnalités
INSERT INTO public.fonctionnalite (est_activée, ident, etat_composite_etat_associe_id, protocole_id) VALUES (true, 'fichier01_lecture_ouverture', (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_ouverture_etat'), (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.fonctionnalite (est_activée, ident, etat_composite_etat_associe_id, protocole_id) VALUES (true, 'fichier01_lecture_lecture', (SELECT ID FROM public.etat WHERE ident = 'fichier01_lecture_lecture_etat'), (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));



-- Gardes
INSERT INTO public.garde (expression) VALUES ('(&i_fin_de_fic&==_faux_)');
INSERT INTO public.garde (expression) VALUES ('(&i_fin_de_fic&==_vrai_)');


--protocol_signal 
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_lire_ligne') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fin_fichier') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_l') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'fermer_l') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'fermer_l_R') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));
INSERT INTO public.protocole_signal (signal_id, protocole_id) VALUES ((SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_l') , (SELECT ID FROM public.protocole WHERE ident = 'fichier01_lecture'));

-- Transitions
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t01', null, (SELECT ID FROM public.etat WHERE ident = 'ouverture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l'), (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t02', null, (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l_R'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l_R'), (SELECT ID FROM public.etat WHERE ident = 'ouverture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t03', null, (SELECT ID FROM public.etat WHERE ident = 'lecture_ligne_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_lire_ligne'), (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t04', null, (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), (SELECT ID FROM public.garde WHERE expression = '(&i_fin_de_fic&==_faux_)'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_ligne'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R'), (SELECT ID FROM public.etat WHERE ident = 'lecture_ligne_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t05', null, (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), (SELECT ID FROM public.garde WHERE expression = '(&i_fin_de_fic&==_vrai_)'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fin_fichier'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R'), (SELECT ID FROM public.etat WHERE ident = 'lecture_ligne_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t06', null, (SELECT ID FROM public.etat WHERE ident = 'fermeture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'fermer_l'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_fermer_l'), (SELECT ID FROM public.etat WHERE ident = 'ouvert' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));
INSERT INTO public.transition (dtype, ident, préfixe_de_signal, etat_elementaire_destination_id, garde_id, signal_sortant_id, signal_entrant_id, etat_elementaire_origine_id) 
 VALUES ('TransitionSimple', 'fichier01_lecture_t07', null, (SELECT ID FROM public.etat WHERE ident = '0' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')), null, (SELECT ID FROM public.signal_ WHERE ident = 'dem_s_fermé_l'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_l_R'), (SELECT ID FROM public.etat WHERE ident = 'fermeture_dmd' and automate_id = (SELECT ID FROM public.automate WHERE ident = 'fichier01_lecture_atmt')));

-- Affectations
INSERT INTO public.affectation (expression, parametre_variable_ex_id, transition_id) VALUES ('&e_id_fic&:=_auto-gen_', (SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.transition WHERE ident = 'fichier01_lecture_t01'));

-- Associations
INSERT INTO public.parametre_garde (parametre_id, gardes_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_fin_de_fic'), (SELECT ID FROM public.garde WHERE expression = '(&i_fin_de_fic&==_faux_)'));
INSERT INTO public.parametre_garde (parametre_id, gardes_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_fin_de_fic'), (SELECT ID FROM public.garde WHERE expression = '(&i_fin_de_fic&==_vrai_)'));

INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'dem_e_ouv_p_l'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_chemin_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_fin_de_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'i_ligne'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R'));
INSERT INTO public.parametre_signal (parametre_id, signal_id) VALUES ((SELECT ID FROM public.parametre WHERE ident = 'e_id_fic'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_l'));

-- Connecteurs
INSERT INTO public.connecteur (dtype, ident, nom_canonique, connecteur_composite_parent_id) VALUES ('ConnecteurÉlémentaire', 'fichier01_lecture', 'fr.s3ca.sds01.outils.connecteurs.fichier01.frontal.SessionDeCommandesLocalesFichier01', null);
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne'));
INSERT INTO public.connecteur_elementaire_signal_entrant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_l'));

INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'ouv_p_l_R'));
INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'lire_ligne_R'));
INSERT INTO public.connecteur_elementaire_signal_sortant (connecteur_elementaire_id, signal_id) VALUES ((SELECT ID FROM public.connecteur WHERE ident = 'fichier01_lecture'), (SELECT ID FROM public.signal_ WHERE ident = 'fermer_l_R'));