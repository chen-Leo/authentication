数据库中有一张user_info(用户信息表)和login_info(使用user
_code的登陆信息表，包含login_info_id,user_code,time,used,user_id字段)used -1表示user_code已使用，1表示未使用，没有生成过user_code的话，数据库不存在改记录

登陆只能使用id和密码，id注册成功后会返回
