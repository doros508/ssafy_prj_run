<template>
  <div>
    <h3>게시글 작성하기</h3>
    <form action="/maratalk" method="POST" enctype="multipart/form-data">
      <table>
        <tr>
          <td>제목</td>
          <td>
            <input type="text" name="title" placeholder="제목을 입력하세요" />
          </td>
        </tr>
        <tr>
          <td>내용</td>
          <td>
            <input type="text" name="content" placeholder="내용을 입력하세요" />
          </td>
        </tr>
      </table>
      <input type="file" name="attach" />
      <button>게시글 등록</button>
    </form>
  </div>
</template>

<script setup></script>

<style scoped></style>
