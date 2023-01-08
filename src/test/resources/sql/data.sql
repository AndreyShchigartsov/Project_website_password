INSERT INTO users (id, login, email, password, birth_day, role)
VALUES (1, 'Andrey','Andrey@google.com','647881','1998-05-14','USER'),
       (2, 'Arsen','Arsen@google.com','249162536','1998-04-13','ADMIN'),
       (3, 'Dmitry','Dmitry@google.com','Пенза','1998-11-08','USER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO storage (id, userid, password, website, comment)
VALUES (1, (SELECT id FROM users WHERE login = 'Andrey'), '647881647881', 'Google','пароль для google'),
       (2, (SELECT id FROM users WHERE login = 'Andrey'), '647881647881', 'vk','пароль для вк'),
       (3, (SELECT id FROM users WHERE login = 'Arsen'), '249162536249162536', 'Meta','пароль для мета'),
       (4, (SELECT id FROM users WHERE login = 'Arsen'), '249162536249162536', 'OD','пароль для однокласников'),
       (5, (SELECT id FROM users WHERE login = 'Dmitry'), 'ПензаПенза', 'Amazon','пароль для амазон'),
       (6, (SELECT id FROM users WHERE login = 'Dmitry'), 'ПензаПенза', 'INSAGRAM','пароль для инстраграм');
SELECT SETVAL('storage_id_seq', (SELECT MAX(id) FROM storage));

INSERT INTO chat (id, title, time_creating, login_creating, type)
VALUES (1, 'chatUser1', '2022-12-14', (SELECT login FROM users WHERE login = 'Andrey'), 'PRIVATE'),
       (2, 'chatUser2', '2022-12-14', (SELECT login FROM users WHERE login = 'Arsen'), 'PRIVATE'),
       (3, 'chatUser3', '2022-12-14', (SELECT login FROM users WHERE login = 'Arsen'), 'PRIVATE'),
       (4, 'chatUser4', '2022-12-14', (SELECT login FROM users WHERE login = 'Dmitry'), 'PRIVATE'),
       (5, 'chatUser5', '2022-12-14', (SELECT login FROM users WHERE login = 'Andrey'), 'PRIVATE');
SELECT SETVAL('chat_id_seq', (SELECT MAX(id) FROM chat));

INSERT INTO sms (id, chatid, sms, time, login_sender)
VALUES (1, (SELECT id FROM chat WHERE title = 'chatUser1'),'hello chatUser1','2022-12-14', (SELECT login FROM users WHERE login = 'Andrey')),
       (2, (SELECT id FROM chat WHERE title = 'chatUser3'),'hello chatUser3','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (3, (SELECT id FROM chat WHERE title = 'chatUser2'),'hello chatUser2','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (4, (SELECT id FROM chat WHERE title = 'chatUser5'),'hello chatUser5','2022-12-14', (SELECT login FROM users WHERE login = 'Andrey')),
       (5, (SELECT id FROM chat WHERE title = 'chatUser2'),'hello chatUser2','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (6, (SELECT id FROM chat WHERE title = 'chatUser5'),'hello chatUser5','2022-12-14', (SELECT login FROM users WHERE login = 'Andrey')),
       (7, (SELECT id FROM chat WHERE title = 'chatUser4'),'hello chatUser4','2022-12-14', (SELECT login FROM users WHERE login = 'Dmitry')),
       (8, (SELECT id FROM chat WHERE title = 'chatUser3'),'hello chatUser3','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (9, (SELECT id FROM chat WHERE title = 'chatUser1'),'hello chatUser1','2022-12-14', (SELECT login FROM users WHERE login = 'Andrey')),
       (10, (SELECT id FROM chat WHERE title = 'chatUser3'),'hello chatUser3','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (11, (SELECT id FROM chat WHERE title = 'chatUser1'),'hello chatUser1','2022-12-14', (SELECT login FROM users WHERE login = 'Andrey')),
       (12, (SELECT id FROM chat WHERE title = 'chatUser3'),'hello chatUser3','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (13, (SELECT id FROM chat WHERE title = 'chatUser2'),'hello chatUser2','2022-12-14', (SELECT login FROM users WHERE login = 'Arsen')),
       (14, (SELECT id FROM chat WHERE title = 'chatUser4'),'hello chatUser4','2022-12-14', (SELECT login FROM users WHERE login = 'Dmitry'));
SELECT SETVAL('sms_id_seq', (SELECT MAX(id) FROM sms));

INSERT INTO user_chat(id, user_id, chat_id, created_at)
VALUES (1, (SELECT id FROM users WHERE login = 'Andrey'), (SELECT id FROM chat WHERE title = 'chatUser1'),'2022-11-17 00:18:29'),
       (2, (SELECT id FROM users WHERE login = 'Andrey'), (SELECT id FROM chat WHERE title = 'chatUser1'),'2022-11-17 00:18:29'),
       (3, (SELECT id FROM users WHERE login = 'Andrey'), (SELECT id FROM chat WHERE title = 'chatUser1'),'2022-11-17 00:18:29'),
       (4, (SELECT id FROM users WHERE login = 'Arsen'), (SELECT id FROM chat WHERE title = 'chatUser2'),'2022-11-17 00:18:29'),
       (5, (SELECT id FROM users WHERE login = 'Arsen'), (SELECT id FROM chat WHERE title = 'chatUser2'),'2022-11-17 00:18:29'),
       (6, (SELECT id FROM users WHERE login = 'Arsen'), (SELECT id FROM chat WHERE title = 'chatUser2'),'2022-11-17 00:18:29'),
       (7, (SELECT id FROM users WHERE login = 'Dmitry'), (SELECT id FROM chat WHERE title = 'chatUser3'),'2022-11-17 00:18:29'),
       (8, (SELECT id FROM users WHERE login = 'Dmitry'), (SELECT id FROM chat WHERE title = 'chatUser3'),'2022-11-17 00:18:29'),
       (9, (SELECT id FROM users WHERE login = 'Dmitry'), (SELECT id FROM chat WHERE title = 'chatUser3'),'2022-11-17 00:18:29');