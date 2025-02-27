INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone, role) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'TEACHER', 'admin', 'Admin User', '936fbbc7-7c93-4eca-b6e5-edff152598e6', '$2a$12$esBpHvu2MVjsRcGTvVgI4uBFoRziO0CSSQh3cETBwK9tuB.0ssm6O', '+0000000000', 'ADMIN');
