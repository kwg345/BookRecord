# BookRecord（借りた本の記録アプリ）

## 前提
- 個人利用の Web アプリ

## 目的
- 図書館で借りた本を個人で記録
  - 借りた日や返却日の管理
  - お気に入り本の記録

## 概要
- フォームから入力した投稿内容をデータベースに保存
- トップページで一覧表示・削除・お気に入り切替が可能
- **主な機能**
  - タイトル、著者の入力必須バリデーション
  - メモは50文字以内に制限
  - お気に入り登録/解除
  - 返却予定日を借りた日 + 14日で自動計算
  - 削除ボタン押下時に確認ポップアップ表示

## 開発環境
- Spring Boot
- データベース：H2 Database
- テンプレートエンジン：Thymeleaf
- 言語：Java
- IDE：Spring Tool Suite / IntelliJ IDEA

## 使用ファイル
- SQL: `schema.sql`, `data.sql`
- Java: `Post.java`, `PostController.java`, `PostRepository.java`
- HTML: `form.html`

## 使用方法
1. Eclipse でプロジェクトをインポート
2. プロジェクトを右クリック → 実行 → Spring Boot アプリケーション
3. ブラウザで http://localhost:8080 にアクセス

