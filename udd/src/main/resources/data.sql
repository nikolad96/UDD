insert into role values (1, 'ROLE_USER');
insert into role values (2, 'ROLE_ADMIN');
insert into role values (3, 'ROLE_RECENZENT');
insert into role values (4, 'ROLE_UREDNIK');

--insert into user_roles values (1,1);
--insert into user_roles values (2,4);
--insert into user_roles values (3,3);
--insert into user_roles values (4,3);

INSERT INTO `udd`.`user` (`id`, `aktiviran`, `drzava`, `email`, `grad`, `ime`, `naucna_oblast`, `password`, `prezime`, `recenzent`, `titula`, `username`) VALUES ('1', false, 'Srbija', 'nikola.djordjevic04@gmail.com', 'Novi Sad', 'Nikola', 'Geografija', '$2a$10$Uy11a9QSNHrB2Z4kC5UdDu3/QmFZu3hB/TWko1mq4.GhafzQ6jWcG', 'djordjevic', false, 'nista', 'nikola321');
INSERT INTO `udd`.`user` (`id`, `aktiviran`, `drzava`, `email`, `grad`, `ime`, `naucna_oblast`, `password`, `prezime`, `recenzent`, `titula`, `username`) VALUES ('2', true, 'Srbija', 'nikola.djordjevic04@gmail.com', 'Novi Sad', 'Urednić', 'Geografija', '$2a$10$Uy11a9QSNHrB2Z4kC5UdDu3/QmFZu3hB/TWko1mq4.GhafzQ6jWcG', 'urednikovic', false, 'nista', 'urednik321');
INSERT INTO `udd`.`user` (`id`, `aktiviran`, `drzava`, `email`, `grad`, `ime`, `naucna_oblast`, `password`, `prezime`, `recenzent`, `titula`, `username`) VALUES ('3', true, 'Srbija', 'nikola.djordjevic04@gmail.com', 'Novi Sad', 'Recenzent', 'Geografija', '$2a$10$Uy11a9QSNHrB2Z4kC5UdDu3/QmFZu3hB/TWko1mq4.GhafzQ6jWcG', 'recenznett', false, 'nista', 'recenzent1');
INSERT INTO `udd`.`user` (`id`, `aktiviran`, `drzava`, `email`, `grad`, `ime`, `naucna_oblast`, `password`, `prezime`, `recenzent`, `titula`, `username`) VALUES ('4', true, 'Srbija', 'nikola.djordjevic04@gmail.com', 'Novi Sad', 'Recenzent2', 'Geografija', '$2a$10$Uy11a9QSNHrB2Z4kC5UdDu3/QmFZu3hB/TWko1mq4.GhafzQ6jWcG', 'recenzentt', false, 'nista', 'recenzent2');

INSERT INTO `udd`.`casopis` (`id`, `naziv`,`oblast`, `open`) VALUES ('1','Nacionalna geografija', 'Geografija',true);
INSERT INTO `udd`.`casopis` (`id`, `naziv`,`oblast`, `open`) VALUES ('2','Casopis 2','Geografija', true);
INSERT INTO `udd`.`casopis` (`id`, `naziv`,`oblast`, `open`) VALUES ('3','Casopis 3','Geografija', false);



INSERT INTO `udd`.`rad` (`id`, `naziv`,`oblast`,`apstrakt`,`autor`,`kljucne`,`lon`,`lat`) VALUES ('1','Rad Broj 1','Geografija','apstrakt1','Nikola','kljucna1 kljucna2 kljucna3 kljucna4',10.0001,10.0001);
INSERT INTO `udd`.`rad` (`id`, `naziv`,`oblast`,`apstrakt`,`autor`,`kljucne`,`lon`,`lat`) VALUES ('2','Rad Broj 2','Geografija','apstrakt2','Nikolić','kljucna1 kljucna2 kljucna5 kljucna6',10.0001,10.0001);
INSERT INTO `udd`.`rad` (`id`, `naziv`,`oblast`,`apstrakt`,`autor`,`kljucne`,`lon`,`lat`) VALUES ('3','Rad 3','Geografija','apstrakt3','Nikola','kljucna5 kljucna6 kljucna3 kljucna4',15.0001,15.0001 );
INSERT INTO `udd`.`rad` (`id`, `naziv`,`oblast`,`apstrakt`,`autor`,`kljucne`,`lon`,`lat`) VALUES ('4','Rad 4','Geografija','apstrakt4','Nikola', 'kljucna7 kljucna8 kljucna9 kljucna5',15.0001,10.0001);

INSERT INTO `udd`.`recenzent` (`id`, `ime`,`longitude`,`latitude`,`rad_id`) VALUES ('1','Milan',10.0001,10.0001,1);
INSERT INTO `udd`.`recenzent` (`id`, `ime`,`longitude`,`latitude`,`rad_id`) VALUES ('2','Vukasin',15.0001,10.0001,1);
INSERT INTO `udd`.`recenzent` (`id`, `ime`,`longitude`,`latitude`,`rad_id`) VALUES ('3','Dovla',10.0001,15.0001,1);
INSERT INTO `udd`.`recenzent` (`id`, `ime`,`longitude`,`latitude`,`rad_id`) VALUES ('4','Goran',10.0002,10.0002,1);