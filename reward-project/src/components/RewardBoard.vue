<template>
  <main class="reward-data-con">
    <div>
      <button type="button" @click="logout">로그아웃</button>
    </div>
    <div>
      <button type="button" class="login-btn" @click="createReward">보상지급받기</button>
    </div>
    <div class="reward-data-box">
      <h1>검색날짜 날짜 [{{ date }}]</h1>
      <h2>지급받은 사용자 목록 조회</h2>
      <div>
        검색 날짜(yyyy-mm-dd) : <input type="text" placeholder="검색날짜" v-model="date"/>
        테이블 행 클릭시 상세정보를 볼 수 있습니다.
        <button type="button" @click="getRewardList">검색</button>
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
      rewards: [],
      memberId: JSON.parse(localStorage.getItem('member-data')).id
    }
  },
  created() {
    if (!localStorage.getItem('member-data')) {
      router.push({path: '/login'})
    }
  },
  mounted() {
    this.getRewardList();
  },
  methods: {
    logout() {
      this.$http({
        url: 'http://localhost:8080/auth/logout',
        method: 'PATCH',
        data: {
          memberId: this.memberId
        }
      }).then(() => {
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
        this.rewards = result.data;
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    },
    getReward(item) {
      this.$router.push({
        path: '/board/detail',
        query: {
          rewardId: item.id
        }
      });
    },
    createReward() {
      this.$http({
        url: 'http://localhost:8080/api/rewards',
        method: 'POST',
        data: {
          memberId: this.memberId
        }
      }).then(() => {
        this.$forceUpdate();
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

.login-btn {
  margin: 0 100px;
  width: 310px;
  display: inline-block;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  background-color: #007bff;
  border: 1px solid transparent;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 10px;
  color: #fff;
  cursor: pointer;
}
</style>
