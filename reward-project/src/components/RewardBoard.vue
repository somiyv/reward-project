<template>
  <main class="reward-data-con">
    <div>
      <button type="button" class="login-btn" @click="logout">로그아웃</button>
    </div>
    <div class="reward-data-box">
      <h1>검색날짜 날짜 [{{ date }}]</h1>
      <h2>지급받은 사용자 목록 조회</h2>
      <div>
        검색 날짜(yyyy-mm-dd) : <input type="text" placeholder="검색날짜" v-model="date"/>
        <button type="button" class="login-btn" @click="getRewardList">검색</button>
      </div>
      <div class="reward-data-section">
        <table>
          <thead>
          <tr>
            <th v-for="(header, index) in headers" :key="index">{{ header.text }}</th>
          </tr>
          </thead>
          <tbody v-if="rewards">
          <tr @click="getReward(item)" class="cursor" :key="index" v-for="(item, index) in rewards">
            <td>{{ item.id }}</td>
            <td>{{ item.member.name }}</td>
            <td>{{ item.point }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </main>
</template>

<script>
import router from "@/router";
import moment from 'moment';

export default {
  name: 'RewardBoard',
  props: {},
  data() {
    return {
      date: moment(new Date(), 'YYYYMMDD').format('YYYY-MM-DD'),
      userId: null,
      password: null,
      headers: [
        {text: 'ID', align: 'center', sortable: false, value: 'id', width: '10%'},
        {text: '사용자명', align: 'center', value: 'name', sortable: false, width: '10%'},
        {text: '보상포인트', align: 'center', value: 'point', sortable: false, width: '10%'}
      ],
      rewards: []
    }
  },
  mounted() {
    this.getRewardList();
  },
  methods: {
    logout() {
      this.$http({
        url: 'http://localhost:8080/auth/logout',
        method: 'PATCH'
      }).then((result) => {
        console.log(result.data)
        localStorage.removeItem('member-data');
        router.push({path: '/login'})
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    },
    getRewardList() {
      this.$http({
        url: 'http://localhost:8080/api/rewards',
        method: 'GET',
        params: {
          createDate: this.date,
          sort: 'asc'
        }
      }).then((result) => {
        console.log(result.data)
        this.rewards = result.data;
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    },
    getReward(item) {
      console.log('item >>', item)
      this.$http({
        url: `http://localhost:8080/api/rewards/${item.id}`,
        method: 'GET',
        params: {
          createDate: this.date,
          sort: 'asc'
        }
      }).then((result) => {
        console.log(result.data)
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    }
  }
}
</script>

<style scoped>
table {
  width: 100%;
  border-top: 1px solid #444444;
  border-collapse: collapse;
}

th, td {
  text-align: center;
  border-bottom: 1px solid #444444;
  padding: 10px;
}
</style>
