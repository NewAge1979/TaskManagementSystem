INSERT INTO public.tasks(title, description, status, priority, creator, executor, created_at, update_at)
VALUES
('Заголовок задачи.', 'Описание задачи.', 'AWAITING', 'LOW', 1, 3, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'FINISHED', 'NORMAL', 1, 4, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'RUNNING', 'LOW', 1, 5, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'FINISHED', 'HIGH', 1, 1, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'NEW', 'LOW', 1, 3, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'NEW', 'NORMAL', 1, 4, Now(), Now()),
('Заголовок задачи.', 'Описание задачи.', 'NEW', 'LOW', 1, 5, Now(), Now());
