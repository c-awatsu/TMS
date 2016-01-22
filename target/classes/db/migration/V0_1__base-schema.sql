create table account (
	--サロゲートキー
	account_id serial primary key,
	--ログインID(ユニーク)
	login_id varchar(32) unique not null,
	--パスワード
	passphrase varchar(256) not null,
	--ニックネーム--
	nickname varchar(32) not null
);

create table product(
	--サロゲートキー
	product_id serial primary key,
	--製品名
	name varchar(256) not null unique
);

create table test(
	--サロゲートキー
	test_id serial primary key,
	--
	classification varchar(256),
	--
	step varchar(1024),
	--
	expected_output varchar(1024),
	--
	product_id integer not null,

	foreign key (product_id)references product(product_id) on delete cascade,
	foreign key (product_id)references product(product_id) on update cascade
);

create table test_record(
	--サロゲートキー
	test_record_id serial primary key,
	--日付
	test_date date,
	--テスト実施者
	tester_id integer,
	--テスト結果
	result boolean,
	--備考
	note varchar(1024),
	--テストアイテムId(外部キー)
	test_id integer,

	foreign key (test_id) references test(test_id) on delete cascade,
	foreign key (test_id) references test(test_id) on update cascade
);