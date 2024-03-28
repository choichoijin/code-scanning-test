INSERT INTO group_code (group_code_id, group_code_name)
VALUES (1, '상태'), (2, '구분');

INSERT INTO code (group_code_id, code_value, code_name)
VALUES (1, 1, '전사'), (1, 2, '팀'), (1, 3, '프로젝트'), (1, 4, '개인'),
       (2, 1, 'ToDo'), (2, 2, 'InProgress'), (2, 3, 'Done'), (2, 4, 'Canceled');

INSERT INTO action_items (shared_yn, type_group_code_id, type_code_value, status_group_code_id, status_code_value, title, body, due_date, password, created_at, updated_at)
VALUES
    ('Y', 1, 1, 2, 1, 'Sample Action Item 1', 'This is a sample body for action item 1', '2024-03-25', '1234', '2024-03-20 21:15:36.108035', '2024-03-20 21:15:36.108035'),
    ('N', 1, 2, 2, 2, 'Sample Action Item 2', 'This is a sample body for action item 2', '2024-04-03', '1234', '2024-03-21 22:15:36.108035', '2024-03-21 22:15:36.108035'),
    ('Y', 1, 3, 2, 3, 'Sample Action Item 3', 'This is a sample body for action item 3', '2024-03-27', '1234', '2024-03-24 21:50:36.108035', '2024-03-24 21:50:36.108035');