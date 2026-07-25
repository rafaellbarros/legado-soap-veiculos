-- =============================================
-- SCRIPT DE POPULAÇÃO DA TABELA VEICULOS
-- =============================================

-- Criação da tabela (se não existir)
CREATE TABLE IF NOT EXISTS veiculos (
    id BIGSERIAL PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    renavam VARCHAR(11) NOT NULL UNIQUE,
    cor VARCHAR(30) NOT NULL,
    modelo VARCHAR(50) NOT NULL
);

-- =============================================
-- INSERÇÃO DE 30 VEÍCULOS (RENAVAM ÚNICOS)
-- =============================================

INSERT INTO veiculos (placa, renavam, cor, modelo) VALUES
-- Carros populares (1-5)
('ABC-1234', '12345678901', 'Prata', 'Fiat Uno'),
('DEF-5678', '23456789012', 'Branco', 'Chevrolet Onix'),
('GHI-9012', '34567890123', 'Preto', 'Hyundai HB20'),
('JKL-3456', '45678901234', 'Vermelho', 'Ford Ka'),
('MNO-7890', '56789012345', 'Azul', 'Volkswagen Gol'),

-- Carros médios (6-10)
('PQR-1234', '67890123456', 'Cinza', 'Toyota Corolla'),
('STU-5678', '78901234567', 'Preto', 'Honda Civic'),
('VWX-9012', '89012345678', 'Branco', 'Nissan Sentra'),
('YZA-3456', '90123456789', 'Prata', 'Chevrolet Cruze'),
('BCD-7890', '11234567890', 'Vermelho', 'Ford Focus'),

-- Carros de luxo (11-15)
('EFG-1234', '12234567891', 'Preto', 'BMW X5'),
('HIJ-5678', '13234567892', 'Branco', 'Mercedes-Benz C180'),
('KLM-9012', '14234567893', 'Cinza', 'Audi A3'),
('NOP-3456', '15234567894', 'Azul', 'Lexus IS'),
('QRS-7890', '16234567895', 'Prata', 'Porsche Cayenne'),

-- SUVs e Pickups (16-20)
('TUV-1234', '17234567896', 'Preto', 'Jeep Compass'),
('WXY-5678', '18234567897', 'Branco', 'Toyota SW4'),
('ZAB-9012', '19234567898', 'Vermelho', 'Ford Ranger'),
('CDE-3456', '20234567899', 'Cinza', 'Chevrolet Tracker'),
('FGH-7890', '21234567900', 'Azul', 'Hyundai Creta'),

-- Carros esportivos (21-25)
('IJK-1234', '22234567901', 'Amarelo', 'Chevrolet Camaro'),
('LMN-5678', '23234567902', 'Vermelho', 'Ford Mustang'),
('OPQ-9012', '24234567903', 'Preto', 'Dodge Challenger'),
('RST-3456', '25234567904', 'Branco', 'Nissan GT-R'),
('UVW-7890', '26234567905', 'Azul', 'Porsche 911'),

-- Carros elétricos/híbridos (26-30)
('XYZ-1234', '27234567906', 'Branco', 'Tesla Model 3'),
('AAA-5678', '28234567907', 'Preto', 'Chevrolet Bolt'),
('BBB-9012', '29234567908', 'Cinza', 'BYD Dolphin'),
('CCC-3456', '30234567909', 'Azul', 'Hyundai Ioniq 5'),
('DDD-7890', '31234567910', 'Prata', 'Renault Zoe');

-- =============================================
-- CONSULTA DE VERIFICAÇÃO
-- =============================================
DO $$
DECLARE
    total_registros INTEGER;
BEGIN
    SELECT COUNT(*) INTO total_registros FROM veiculos;
    RAISE NOTICE 'Total de veículos cadastrados: %', total_registros;
END $$;

-- Exibe os primeiros 10 registros como exemplo
SELECT * FROM veiculos LIMIT 10;