drop table affectation cascade;
drop table connecteur_elementaire_signal_entrant cascade;
drop table connecteur_elementaire_signal_sortant cascade;
drop table connecteur cascade;
drop table automate cascade;
drop table commande_haut_niveau cascade;
drop table commande_haut_niveau_argument cascade;
drop table etat cascade;
drop table etat_transition_rdv cascade;
drop table fonctionnalite cascade;
drop table garde cascade;
drop table parametre cascade;
drop table parametre_affectation cascade;
drop table parametre_garde cascade;
drop table parametre_signal cascade;
drop table parametre_test_sur_parametre cascade;
drop table test_sur_parametres cascade;
drop table protocole cascade;
drop table signal_ cascade;
drop table transition cascade;
drop table valeur_de_parametre cascade;
drop table contextedexecution cascade;
drop table contexte_execution_applique_fonctionnalite cascade;
drop table utilisation_de_contextedexecution_applique cascade;
drop table utilisation_de_contextedexecution_constate cascade;
drop table partenaire cascade;
drop table image_de_recursivite_noyau cascade;
drop table ligne_de_journal_apres cascade ;
drop table image_de_gds cascade ;
drop table commande_couche_haute cascade;
drop table cmd_couche_haute_utilisation_ctx_de_execution_applique cascade;

delete from affectation;
delete from connecteur_elementaire_signal_entrant;
delete from connecteur_elementaire_signal_sortant;
delete from connecteur;
delete from automate;
delete from commande_haut_niveau;
delete from commande_haut_niveau_argument;
delete from etat;
delete from etat_transition_rdv;
delete from fonctionnalite_parametre;
delete from fonctionnalite;
delete from garde;
delete from parametre;
delete from parametre_affectation;
delete from parametre_garde;
delete from parametre_signal;
delete from parametre_test_sur_parametre;
delete from test_sur_parametres;
delete from protocole_signal;
delete from protocole;
delete from signal_;
delete from transition;
delete from valeur_de_parametre;
delete from contextedexecution;
delete from contexte_execution_applique_fonctionnalite;
delete from utilisation_de_contextedexecution_applique;
delete from utilisation_de_contextedexecution_constate;
delete from partenaire;
delete from image_de_recursivite_noyau;
delete from ligne_de_journal_apres ; 
delete from image_de_gds ;
delete from commande_couche_haute;
delete from cmd_couche_haute_utilisation_ctx_de_execution_applique;

create table affectation (id  serial not null, expression varchar(255), parametre_variable_ex_id int4, transition_id int4, primary key (id));
create table automate (id  serial not null, est_de_gds boolean, ident varchar(255), primary key (id));
create table commande_haut_niveau (id  serial not null, description varchar(255), ident varchar(255), primary key (id));
create table commande_haut_niveau_argument (id  serial not null, description varchar(255), ident varchar(255), obligatoire boolean not null, type varchar(255), valeur_par_defaut varchar(255), commande_hau_niveau_id int4 not null, primary key (id));
create table connecteur (dtype varchar(31) not null, id  serial not null, ident varchar(255), nom_canonique varchar(255), connecteur_composite_parent_id int4, primary key (id));
create table connecteur_elementaire_signal_entrant (connecteur_elementaire_id int4 not null, signal_id int4 not null);
create table connecteur_elementaire_signal_sortant (connecteur_elementaire_id int4 not null, signal_id int4 not null);
create table contextedexecution (dtype varchar(31) not null, id  serial not null, ident varchar(255), expr_logique varchar(255), precise int4, primary key (id));
create table contexte_execution_applique_fonctionnalite (fonctionnalite_id int4 not null, contexte_execution_applique_id int4 not null);
create table etat (dtype varchar(31) not null, id  serial not null, ident varchar(255), chemin varchar(255), etat_composite_parent_id int4, automate_id int4, primary key (id));
create table etat_transition_rdv (etat_elementaire_id int4 not null, transition_rdv_id int4 not null, primary key (etat_elementaire_id, transition_rdv_id));
create table fonctionnalite (id  serial not null, est_activée boolean, ident varchar(255), etat_composite_etat_associe_id int4, protocole_id int4, primary key (id));
create table garde (id  serial not null, expression varchar(255), primary key (id));
create table parametre (id  serial not null, est_implicitement_objet_de_flux boolean, est_obligatoire boolean, expr_implicite_si_facult varchar(255), ident varchar(255), type_ varchar(255), protocole_id int4, primary key (id));
create table parametre_affectation (parametre_id int4 not null, affectations_id int4 not null);
create table parametre_garde (parametre_id int4 not null, gardes_id int4 not null);
create table parametre_signal (parametre_id int4 not null, signal_id int4 not null);
create table parametre_test_sur_parametre (parametre_id int4 not null, test_sur_parametre_id int4 not null);
create table partenaire (id  serial not null, comme_appelant int4, comme_appele int4, primary key (id));
create table protocole (id  serial not null, ident varchar(255), automate_id int4, primary key (id));
create table signal_ (id  serial not null, ident varchar(255), sens boolean, protocole_id int4, primary key (id));
create table test_sur_parametres (id  serial not null, expr_logique_de_comparaison varchar(255), contextedexecution_constate_id int4, primary key (id));
create table transition (dtype varchar(31) not null, id  serial not null, ident varchar(255), préfixe_de_signal varchar(255), etat_elementaire_destination_id int4, garde_id int4, signal_sortant_id int4, etat_elementaire_origine_id int4, signal_entrant_id int4, primary key (id));
create table utilisation_de_contextedexecution_applique (dtype varchar(31) not null, id  serial not null, ident varchar(255), sorte varchar(255), contextedexecution_applique_utilise int4, primary key (id));
create table utilisation_de_contextedexecution_constate (dtype varchar(31) not null, id  serial not null, ident varchar(255), sorte varchar(255), contextedexecution_constate_utilise int4, primary key (id));
create table valeur_de_parametre (id  serial not null, valeur_sérialisée_chn_car varchar(255), ident_et_type int4, contextedexecution_applique_id int4, primary key (id));
create table fonctionnalite_parametre (fonctionnalite_id int4 not null, parametre_id int4 not null);
create table image_de_reculigne_de_journal_apresrsivite_noyau (id serial not null, image_de_gds_createur_id integer, image_de_gds_cree_id integer, ligne_de_journal_apres_notification_id integer, primary key (id)) ;
create table ligne_de_journal_apres (id serial not null, date_heure_ms integer not null, valeurs_complémentairesjson character varying(255), contexte_constate_id integer, etat_atteint_id integer, primary key (id));
create table image_de_gds (id serial not null, ident_gds character varying(255), ligne_de_journal_apres_confirmation_id integer, primary key (id));
create table commande_couche_haute (id serial not null, ident_session_noyau character varying(255), verbosité_demandée integer not null, image_associe_id integer, primary key (id));
create table cmd_couche_haute_utilisation_ctx_de_execution_applique (commande_couche_haute_id integer not null, utilisation_de_contextedexecution_applique_id integer not null);

alter table protocole drop constraint UK_g79sbatdnkcy04cvniet7qqnx;
alter table protocole add constraint UK_g79sbatdnkcy04cvniet7qqnx unique (ident);
alter table affectation add constraint FKk1k23o0b5wypj8p337tajsthd foreign key (parametre_variable_ex_id) references parametre;
alter table affectation add constraint FKcoxl6rey0sqtwcptldiideot1 foreign key (transition_id) references transition;
alter table commande_haut_niveau_argument add constraint FKcfmyb63gohreu8m6co0ydt01u foreign key (commande_hau_niveau_id) references commande_haut_niveau;
alter table connecteur add constraint FK4gyyl5xvrgvb5mju05brwms9 foreign key (connecteur_composite_parent_id) references connecteur;
alter table connecteur_elementaire_signal_entrant add constraint FK2e0uluaaj9m43scjp6ck9jtac foreign key (signal_id) references signal_;
alter table connecteur_elementaire_signal_entrant add constraint FK826mpl6yb7tk74rrgw5ge4t63 foreign key (connecteur_elementaire_id) references connecteur;
alter table connecteur_elementaire_signal_sortant add constraint FKsccsjcpnglqs6sxdhr4my9ojv foreign key (signal_id) references signal_;
alter table connecteur_elementaire_signal_sortant add constraint FKnuu2urhxf74wylkk19qxdtq7 foreign key (connecteur_elementaire_id) references connecteur;
alter table contextedexecution add constraint FK9e3q60ddy4vgm3y14kh3hpoe8 foreign key (precise) references contextedexecution;
alter table contexte_execution_applique_fonctionnalite add constraint FK2lxxtx0nj6suh5upmkncgox1a foreign key (contexte_execution_applique_id) references contextedexecution;
alter table contexte_execution_applique_fonctionnalite add constraint FKhl33sjltsxfqdvu43vxm5ci0g foreign key (fonctionnalite_id) references fonctionnalite;
alter table etat add constraint FKljnyp52wxegft5aeyv5ucdjuy foreign key (etat_composite_parent_id) references etat;
alter table etat add constraint FK1halkxb5yc4c6cas50fh408gc foreign key (automate_id) references automate;
alter table etat_transition_rdv add constraint FKjnj3gac6694q8ila2ef9qr8cw foreign key (transition_rdv_id) references transition;
alter table etat_transition_rdv add constraint FKoinf88ffj4p2t9di75gkntm0g foreign key (etat_elementaire_id) references etat;
alter table fonctionnalite add constraint FKeyqehuu4xh4q8jf8bn6q0cdt9 foreign key (etat_composite_etat_associe_id) references etat;
alter table fonctionnalite add constraint FK4l959emf375m9cmwjaf9lflx5 foreign key (protocole_id) references protocole;
alter table parametre add constraint FKlt1cgwk2uk68lnwysrs3lntr8 foreign key (protocole_id) references protocole;
alter table parametre_affectation add constraint FKonp13qq9lgh3h5acfpelnjv3s foreign key (affectations_id) references affectation;
alter table parametre_affectation add constraint FKdquops8xo0y8iw1wj22o670am foreign key (parametre_id) references parametre;
alter table parametre_garde add constraint FKm5kebol9gcjpo22147dfpapgo foreign key (gardes_id) references garde;
alter table parametre_garde add constraint FKs9ifas4febis5qyucyjv4jpdi foreign key (parametre_id) references parametre;
alter table parametre_signal add constraint FKncl78kogh09xmx3woxusl0o5n foreign key (signal_id) references signal_;
alter table parametre_signal add constraint FKkx1jlcv8fq9fspahu03ebn6wo foreign key (parametre_id) references parametre;
alter table parametre_test_sur_parametre add constraint FK1g88neqex76hmydwvc9b4eioi foreign key (test_sur_parametre_id) references test_sur_parametres;
alter table parametre_test_sur_parametre add constraint FKt0u7n0vne1g8b1t92exta5162 foreign key (parametre_id) references parametre;
alter table partenaire add constraint FKmi81sep7rxacu9y6q7crwii02 foreign key (comme_appelant) references utilisation_de_contextedexecution_constate;
alter table partenaire add constraint FK9fqw8adrsmx4hekqtjwquxh6 foreign key (comme_appele) references utilisation_de_contextedexecution_applique;
alter table protocole add constraint FKkj8fda1khh7nugjsokj44bwf4 foreign key (automate_id) references automate;
alter table signal_ add constraint FKlf09qvd2mx3s91dckcatt2rxs foreign key (protocole_id) references protocole;
alter table test_sur_parametres add constraint FK3iq8usjblyxwl3k7ep0k8bsa4 foreign key (contextedexecution_constate_id) references contextedexecution;
alter table transition add constraint FKkhhyd27aqc92hkn5ajy8ektcu foreign key (etat_elementaire_destination_id) references etat;
alter table transition add constraint FKfhd2k6pq5pcx9fcr0rmbl66yr foreign key (garde_id) references garde;
alter table transition add constraint FKitxakl90svh3ix73bwlg73bu8 foreign key (signal_sortant_id) references signal_;
alter table transition add constraint FK89n72lsh57d8wq1ogdsrfenpd foreign key (etat_elementaire_origine_id) references etat;
alter table transition add constraint FKtos1fdrxntlfv8a4brchc0t5e foreign key (signal_entrant_id) references signal_;
alter table utilisation_de_contextedexecution_applique add constraint FKo3he37cdo6bjbxy8980ft4cb8 foreign key (contextedexecution_applique_utilise) references contextedexecution;
alter table utilisation_de_contextedexecution_constate add constraint FK7jm2noow0eqtwrlak5y2f96oi foreign key (contextedexecution_constate_utilise) references contextedexecution;
alter table valeur_de_parametre add constraint FKf05p7p0h2k3q3tj8cu5cydbrt foreign key (ident_et_type) references parametre;
alter table valeur_de_parametre add constraint FKkmjx4fpas2o5iu4nwnhfmdq8x foreign key (contextedexecution_applique_id) references contextedexecution;
alter table fonctionnalite add constraint FKpkq6hemnxk8eoip2l06y2eiwx foreign key (automate_id) references automate;
alter table fonctionnalite_parametre add constraint FKnkfmc73jgl9eurytlf15l9rc9 foreign key (parametre_id) references parametre;
alter table fonctionnalite_parametre add constraint FKm0c1phc5gdi3hkcvyvhtwvgum foreign key (fonctionnalite_id) references fonctionnalite;
alter table image_de_recursivite_noyau add constraint fkahutw2dq2www4pp1m4ojj2hws foreign key (image_de_gds_createur_id) references "image_de_gds" ("id") ;
alter table image_de_recursivite_noyau add constraint fksyk26xsvrvjaik7uvjvg38skd foreign key (image_de_gds_cree_id) references "image_de_gds" ("id") ;
alter table image_de_recursivite_noyau add constraint fkbtn21qogohdk2xsbsrmkti3v6 foreign key (ligne_de_journal_apres_notification_id) references "ligne_de_journal_apres" ("id");
alter table ligne_de_journal_apres add constraint fkapf5hdpvul6bs8d030mty8kf1 foreign key (contexte_constate_id) references "contextedexecution" ("id");
alter table ligne_de_journal_apres add constraint fk8pdkja1q3geyq2m5ut1o52f0c foreign key (etat_atteint_id) references "etat" ("id");
alter table image_de_gds add constraint fkfckg5m01khk5890s66daf4bgr foreign key (ligne_de_journal_apres_confirmation_id) references "ligne_de_journal_apres" ("id");
alter table commande_couche_haute add constraint fk2yv2dgnofu0tj8e2pebincn8h foreign key (image_associe_id) references "image_de_gds" ("id") ;
alter table cmd_couche_haute_utilisation_ctx_de_execution_applique  add constraint fki9hao646b7memlxtwyiik0txp foreign key (utilisation_de_contextedexecution_applique_id) references "utilisation_de_contextedexecution_applique" ("id");
alter table cmd_couche_haute_utilisation_ctx_de_execution_applique  add constraint fk77ouhw6jqtcdb7wmpibnidqu3 foreign key (commande_couche_haute_id) references "commande_couche_haute" ("id");