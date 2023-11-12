-- PostgreSQL database dump
-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 9.5.25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--DROP DATABASE loja_virtual_projeto;

--CREATE DATABASE loja_virtual_projeto WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';

ALTER DATABASE loja_virtual_projeto OWNER TO postgres;

--connect loja_virtual_projeto

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe INTEGER;

BEGIN
      existe = (SELECT COUNT(1) 
		  FROM pessoa_fisica 
		  WHERE id = NEW.id_pessoa);
   IF(existe <= 0) THEN
      existe = (SELECT COUNT(1) 
		  FROM pessoa_juridica 
		  WHERE id = NEW.id_pessoa);
   IF(existe <= 0) THEN
	RAISE EXCEPTION 
	  'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
    END IF;
  END IF;
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;

CREATE FUNCTION public.validachavepessoafornecedor() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE existe INTEGER;

BEGIN
      existe = (SELECT COUNT(1) 
		  FROM pessoa_fisica 
		  WHERE id = NEW.id_pessoa_fornecedor);
   IF(existe <= 0) THEN
      existe = (SELECT COUNT(1) 
		  FROM pessoa_juridica 
		  WHERE id = NEW.id_pessoa_fornecedor);
   IF(existe <= 0) THEN
	RAISE EXCEPTION 
	  'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
    END IF;
  END IF;
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.validachavepessoafornecedor() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE public.acesso (
    id_acesso bigint NOT NULL,
    descricao_acesso character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO postgres;

CREATE TABLE public.avaliacao_produto (
    id_avaliacao_produto bigint NOT NULL,
    descricao_avaliacao_produto character varying(255) NOT NULL,
    nota_avaliacao_produto integer NOT NULL,
    id_pessoa bigint NOT NULL,
    id_produto bigint NOT NULL
);


ALTER TABLE public.avaliacao_produto OWNER TO postgres;

CREATE TABLE public.categoria_produto (
    id_categoria_produto bigint NOT NULL,
    nome_desc character varying(255) NOT NULL
);


ALTER TABLE public.categoria_produto OWNER TO postgres;

CREATE TABLE public.conta_pagar (
    id_conta_pagar bigint NOT NULL,
    descricao_conta_pagar character varying(255) NOT NULL,
    data_pagamento date,
    data_vencimento date NOT NULL,
    status_conta_pagar character varying(255),
    valor_desconto numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    id_pessoa bigint NOT NULL,
    id_pessoa_fornecedor bigint NOT NULL
);


ALTER TABLE public.conta_pagar OWNER TO postgres;


CREATE TABLE public.conta_receber (
    id_conta_receber bigint NOT NULL,
    descricao_conta_receber character varying(255) NOT NULL,
    data_pagamento date NOT NULL,
    data_vencimento date NOT NULL,
    status_conta_receber character varying(255),
    valor_desconto numeric(19,2),
    valor_total numeric(19,2),
    id_pessoa bigint NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO postgres;

CREATE TABLE public.cupom_desconto (
    id_cupom_desconto bigint NOT NULL,
    codigo_descricao character varying(255) NOT NULL,
    data_validade_cupom date NOT NULL,
    valor_porcentagem_desconto numeric(19,2),
    valor_real_desconto numeric(19,2)
);


ALTER TABLE public.cupom_desconto OWNER TO postgres;

CREATE TABLE public.endereco (
    id_endereco bigint NOT NULL,
    bairro character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cidade character varying(255) NOT NULL,
    complemento character varying(255),
    numero character varying(255) NOT NULL,
    rua_logradouro character varying(255) NOT NULL,
    tipo_endereco character varying(255) NOT NULL,
    uf character varying(255) NOT NULL,
    id_pessoa bigint NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;

CREATE TABLE public.forma_pagamento (
    id_forma_pagamento bigint NOT NULL,
    descricao_forma_pagamento character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO postgres;

CREATE TABLE public.imagem_produto (
    id_imagem_produto bigint NOT NULL,
    imagem_miniatura_produto text NOT NULL,
    imagem_original_produto text NOT NULL,
    id_produto bigint NOT NULL
);


ALTER TABLE public.imagem_produto OWNER TO postgres;

CREATE TABLE public.item_venda_loja (
    id_item_venda_loja bigint NOT NULL,
    quantidade_item_venda_loja double precision NOT NULL,
    id_produto bigint NOT NULL,
    id_venda_compra_loja_virtual bigint NOT NULL
);


ALTER TABLE public.item_venda_loja OWNER TO postgres;


CREATE TABLE public.marca_produto (
    id_marca_produto bigint NOT NULL,
    nome_descricao character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO postgres;


CREATE TABLE public.nota_fiscal_compra (
    id_nota_fiscal_compra bigint NOT NULL,
    data_compra date NOT NULL,
    descricao_observacao character varying(255),
    numero_nota character varying(255) NOT NULL,
    serie_nota character varying(255) NOT NULL,
    valor_desconto numeric(19,2),
    valor_icms numeric(19,2) NOT NULL,
    valor_total numeric(19,2) NOT NULL,
    id_conta_pagar bigint NOT NULL,
    id_pessoa bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO postgres;

CREATE TABLE public.nota_fiscal_venda (
    id_nota_fiscal_venda bigint NOT NULL,
    numero_nota_fiscal_venda character varying(255) NOT NULL,
    pdf text NOT NULL,
    serie_nota_fiscal_venda character varying(255) NOT NULL,
    tipo_nota_fiscal_venda character varying(255) NOT NULL,
    xml text NOT NULL,
    id_venda_compra_loja_virtual bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO postgres;

CREATE TABLE public.nota_item_produto (
    id_nota_item_produto bigint NOT NULL,
    qtd_nota_item_produto double precision NOT NULL,
    id_nota_fiscal_compra bigint NOT NULL,
    id_produto bigint NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO postgres;

CREATE TABLE public.pessoa_fisica (
    id bigint NOT NULL,
    email_pess character varying(255) NOT NULL,
    nome_pess character varying(255) NOT NULL,
    telefone_pess character varying(255) NOT NULL,
    cpf_pessoa_fisica character varying(255) NOT NULL,
    data_nascimento date NOT NULL
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

CREATE TABLE public.pessoa_juridica (
    id bigint NOT NULL,
    email_pess character varying(255) NOT NULL,
    nome_pess character varying(255) NOT NULL,
    telefone_pess character varying(255) NOT NULL,
    categoria character varying(255),
    cnpj_pessoa_juridica character varying(255) NOT NULL,
    inscricao_estadual character varying(255) NOT NULL,
    inscricao_municipal character varying(255),
    nome_fantasia character varying(255) NOT NULL,
    razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;


CREATE TABLE public.produto (
    id_produto bigint NOT NULL,
    alerta_qtd_estoque boolean,
    altura_produto double precision NOT NULL,
    status_ativo boolean NOT NULL,
    descricao_produto text NOT NULL,
    largura_produto double precision NOT NULL,
    link_youtube character varying(255),
    nome_produto character varying(255) NOT NULL,
    profundidade_produto double precision NOT NULL,
    qtd_alerta_estoque integer,
    qtd_estoque integer NOT NULL,
    qtd_clique_produto integer,
    tipo_unidade character varying(255) NOT NULL,
    valor_venda numeric(19,2) NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acesso OWNER TO postgres;

CREATE SEQUENCE public.seq_avaliacao_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_avaliacao_produto OWNER TO postgres;

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_categoria_produto OWNER TO postgres;


CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_pagar OWNER TO postgres;


CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_receber OWNER TO postgres;


CREATE SEQUENCE public.seq_cupom_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupom_desconto OWNER TO postgres;

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_endereco OWNER TO postgres;

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_pagamento OWNER TO postgres;

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_imagem_produto OWNER TO postgres;


CREATE SEQUENCE public.seq_item_venda_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_venda_loja OWNER TO postgres;


CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_marca_produto OWNER TO postgres;


CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_compra OWNER TO postgres;

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_venda OWNER TO postgres;

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_nota_item_produto OWNER TO postgres;

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pessoa OWNER TO postgres;

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_produto OWNER TO postgres;

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreio OWNER TO postgres;

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO postgres;

CREATE SEQUENCE public.seq_venda_compra_loja_virtual
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_venda_compra_loja_virtual OWNER TO postgres;

CREATE TABLE public.status_rastreio (
    id_status_rastreio bigint NOT NULL,
    centro_distribuicao_rastreio character varying(255),
    cidade_rastreio character varying(255),
    estado_rastreio character varying(255),
    status_rastreio character varying(255),
    id_venda_compra_loja_virtual bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO postgres;

CREATE TABLE public.usuario (
    id_usuario bigint NOT NULL,
    data_atual_senha_usu date,
    login_usu character varying(255) NOT NULL,
    senha_usu character varying(255) NOT NULL,
    id_pessoa bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

CREATE TABLE public.usuarios_acesso (
    id_usuario bigint NOT NULL,
    id_acesso bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO postgres;

CREATE TABLE public.venda_compra_loja_virtual (
    id_venda_compra_loja_virtual bigint NOT NULL,
    data_entrega date NOT NULL,
    data_venda date NOT NULL,
    dia_entrega integer NOT NULL,
    valor_desconto_vd_cp_lj_vt numeric(19,2),
    valor_frete numeric(19,2) NOT NULL,
    valor_total_vd_cp_lj_vt numeric(19,2) NOT NULL,
    id_cupom_desconto bigint,
    id_endereco_cobranca bigint NOT NULL,
    id_endereco_entrega bigint NOT NULL,
    id_forma_pagamento bigint NOT NULL,
    id_nota_fiscal_venda bigint NOT NULL,
    id_pessoa bigint NOT NULL
);


ALTER TABLE public.venda_compra_loja_virtual OWNER TO postgres;

INSERT INTO public.conta_pagar (id_conta_pagar, descricao_conta_pagar, data_pagamento, data_vencimento, status_conta_pagar, valor_desconto, valor_total, id_pessoa, id_pessoa_fornecedor) VALUES (1, 'dd', '2000-10-10', '2000-10-10', '1', 1.00, 12.00, 1, 1);
INSERT INTO public.conta_pagar (id_conta_pagar, descricao_conta_pagar, data_pagamento, data_vencimento, status_conta_pagar, valor_desconto, valor_total, id_pessoa, id_pessoa_fornecedor) VALUES (2, 'dd', '2000-10-10', '2000-10-10', '1', 1.00, 12.00, 1, 1);

INSERT INTO public.pessoa_fisica (id, email_pess, nome_pess, telefone_pess, cpf_pessoa_fisica, data_nascimento) VALUES (1, 'gabriel.fcosta', 'Gabriel', '51999999', '0352288664', '1996-10-10');

SELECT pg_catalog.setval('public.seq_acesso', 1, false);

SELECT pg_catalog.setval('public.seq_avaliacao_produto', 1, false);

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);

SELECT pg_catalog.setval('public.seq_cupom_desconto', 1, false);

SELECT pg_catalog.setval('public.seq_endereco', 1, false);

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);

SELECT pg_catalog.setval('public.seq_item_venda_loja', 1, false);

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);

SELECT pg_catalog.setval('public.seq_produto', 1, false);

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);

SELECT pg_catalog.setval('public.seq_usuario', 1, false);

SELECT pg_catalog.setval('public.seq_venda_compra_loja_virtual', 1, false);

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id_acesso);

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT avaliacao_produto_pkey PRIMARY KEY (id_avaliacao_produto);

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id_categoria_produto);

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id_conta_pagar);

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id_conta_receber);

ALTER TABLE ONLY public.cupom_desconto
    ADD CONSTRAINT cupom_desconto_pkey PRIMARY KEY (id_cupom_desconto);

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco);

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id_forma_pagamento);

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT imagem_produto_pkey PRIMARY KEY (id_imagem_produto);
	
ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT item_venda_loja_pkey PRIMARY KEY (id_item_venda_loja);

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id_marca_produto);

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id_nota_fiscal_compra);

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id_nota_fiscal_venda);

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id_nota_item_produto);
	
ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id_produto);

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id_status_rastreio);

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT uk_8i2xo8dshlxpfclqasqaan41 UNIQUE (id_acesso);

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT unique_acesso_user UNIQUE (id_usuario, id_acesso);

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT venda_compra_loja_virtual_pkey PRIMARY KEY (id_venda_compra_loja_virtual);

CREATE TRIGGER validachavepessoacontapagar BEFORE INSERT OR UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoacontapagarfornecedor BEFORE INSERT OR UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoafornecedor();

CREATE TRIGGER validachavepessoacontareceber BEFORE INSERT OR UPDATE ON public.conta_receber FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoaendereco BEFORE INSERT OR UPDATE ON public.endereco FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoanotafiscalcompra BEFORE INSERT OR UPDATE ON public.nota_fiscal_compra FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoaupdate BEFORE INSERT OR UPDATE ON public.avaliacao_produto FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoausuario BEFORE INSERT OR UPDATE ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

CREATE TRIGGER validachavepessoavendacompralojavirtual BEFORE INSERT OR UPDATE ON public.venda_compra_loja_virtual FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT fk_acesso FOREIGN KEY (id_acesso) REFERENCES public.acesso(id_acesso);

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT fk_conta_pagar FOREIGN KEY (id_conta_pagar) REFERENCES public.conta_pagar(id_conta_pagar);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT fk_cupom_desconto FOREIGN KEY (id_cupom_desconto) REFERENCES public.cupom_desconto(id_cupom_desconto);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT fk_endereco_cobranca FOREIGN KEY (id_endereco_cobranca) REFERENCES public.endereco(id_endereco);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT fk_endereco_entrega FOREIGN KEY (id_endereco_entrega) REFERENCES public.endereco(id_endereco);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT fk_forma_pagamento FOREIGN KEY (id_forma_pagamento) REFERENCES public.forma_pagamento(id_forma_pagamento);

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT fk_nota_fiscal_compra FOREIGN KEY (id_nota_fiscal_compra) REFERENCES public.nota_fiscal_compra(id_nota_fiscal_compra);

ALTER TABLE ONLY public.venda_compra_loja_virtual
    ADD CONSTRAINT fk_nota_fiscal_venda FOREIGN KEY (id_nota_fiscal_venda) REFERENCES public.nota_fiscal_venda(id_nota_fiscal_venda);

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto);

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto);

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto);

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto);

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT fk_venda_compra_loja_virtual FOREIGN KEY (id_venda_compra_loja_virtual) REFERENCES public.venda_compra_loja_virtual(id_venda_compra_loja_virtual);

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT fk_venda_compra_loja_virtual FOREIGN KEY (id_venda_compra_loja_virtual) REFERENCES public.venda_compra_loja_virtual(id_venda_compra_loja_virtual);

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT fk_venda_compra_loja_virtual FOREIGN KEY (id_venda_compra_loja_virtual) REFERENCES public.venda_compra_loja_virtual(id_venda_compra_loja_virtual);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;