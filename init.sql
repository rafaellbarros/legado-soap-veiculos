-- =============================================
-- SCRIPT DE POPULAÇÃO DA TABELA VEICULOS
-- =============================================

-- Criação da tabela
CREATE TABLE IF NOT EXISTS veiculos (
    id BIGSERIAL PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    renavam VARCHAR(11) NOT NULL UNIQUE,
    cor VARCHAR(30) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    data_desativacao TIMESTAMP
);

-- =============================================
-- INSERÇÃO DE 30 VEÍCULOS
-- =============================================

INSERT INTO veiculos (placa, renavam, cor, modelo, ativo, data_cadastro) VALUES
-- Carros populares
('ABC-1234', '12345678901', 'Prata', 'Fiat Uno', true, CURRENT_TIMESTAMP),
('DEF-5678', '23456789012', 'Branco', 'Chevrolet Onix', true, CURRENT_TIMESTAMP),
('GHI-9012', '34567890123', 'Preto', 'Hyundai HB20', true, CURRENT_TIMESTAMP),
('JKL-3456', '45678901234', 'Vermelho', 'Ford Ka', true, CURRENT_TIMESTAMP),
('MNO-7890', '56789012345', 'Azul', 'Volkswagen Gol', true, CURRENT_TIMESTAMP),

-- Carros médios
('PQR-1234', '67890123456', 'Cinza', 'Toyota Corolla', true, CURRENT_TIMESTAMP),
('STU-5678', '78901234567', 'Preto', 'Honda Civic', true, CURRENT_TIMESTAMP),
('VWX-9012', '89012345678', 'Branco', 'Nissan Sentra', true, CURRENT_TIMESTAMP),
('YZA-3456', '90123456789', 'Prata', 'Chevrolet Cruze', true, CURRENT_TIMESTAMP),
('BCD-7890', '11234567890', 'Vermelho', 'Ford Focus', true, CURRENT_TIMESTAMP),

-- Carros de luxo
('EFG-1234', '12234567891', 'Preto', 'BMW X5', true, CURRENT_TIMESTAMP),
('HIJ-5678', '13234567892', 'Branco', 'Mercedes-Benz C180', true, CURRENT_TIMESTAMP),
('KLM-9012', '14234567893', 'Cinza', 'Audi A3', true, CURRENT_TIMESTAMP),
('NOP-3456', '15234567894', 'Azul', 'Lexus IS', true, CURRENT_TIMESTAMP),
('QRS-7890', '16234567895', 'Prata', 'Porsche Cayenne', true, CURRENT_TIMESTAMP),

-- SUVs e Pickups
('TUV-1234', '17234567896', 'Preto', 'Jeep Compass', true, CURRENT_TIMESTAMP),
('WXY-5678', '18234567897', 'Branco', 'Toyota SW4', true, CURRENT_TIMESTAMP),
('ZAB-9012', '19234567898', 'Vermelho', 'Ford Ranger', true, CURRENT_TIMESTAMP),
('CDE-3456', '20234567899', 'Cinza', 'Chevrolet Tracker', true, CURRENT_TIMESTAMP),
('FGH-7890', '21234567900', 'Azul', 'Hyundai Creta', true, CURRENT_TIMESTAMP),

-- Carros esportivos
('IJK-1234', '22234567901', 'Amarelo', 'Chevrolet Camaro', true, CURRENT_TIMESTAMP),
('LMN-5678', '23234567902', 'Vermelho', 'Ford Mustang', true, CURRENT_TIMESTAMP),
('OPQ-9012', '24234567903', 'Preto', 'Dodge Challenger', true, CURRENT_TIMESTAMP),
('RST-3456', '25234567904', 'Branco', 'Nissan GT-R', true, CURRENT_TIMESTAMP),
('UVW-7890', '26234567905', 'Azul', 'Porsche 911', true, CURRENT_TIMESTAMP),

-- Carros elétricos/híbridos
('XYZ-1234', '27234567906', 'Branco', 'Tesla Model 3', true, CURRENT_TIMESTAMP),
('AAA-5678', '28234567907', 'Preto', 'Chevrolet Bolt', true, CURRENT_TIMESTAMP),
('BBB-9012', '29234567908', 'Cinza', 'BYD Dolphin', true, CURRENT_TIMESTAMP),
('CCC-3456', '30234567909', 'Azul', 'Hyundai Ioniq 5', true, CURRENT_TIMESTAMP),
('DDD-7890', '31234567910', 'Prata', 'Renault Zoe', true, CURRENT_TIMESTAMP);

-- =============================================
-- CONSULTA DE VERIFICAÇÃO
-- =============================================
SELECT COUNT(*) as total_veiculos FROM veiculos;
SELECT placa, renavam, cor, modelo, ativo FROM veiculos LIMIT 10;