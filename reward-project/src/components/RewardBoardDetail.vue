<template>
  <main class="reward-data-con">
    <div class="reward-data-box">
      <h1>보상 데이터 상세 조회</h1>
      <div>
        <li>사용자명 : {{ reward.name }}</li>
        <li>제목 : {{ reward.title }}</li>
        <li>상세설명 : {{ reward.description }}</li>
        <li>지급 포인트 : {{ reward.point }}</li>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "RewardBoardDetail",
  props: {},
  data() {
    return {
      rewardId: null,
      reward: {
        id: null,
        title: null,
        description: null,
        point: 0,
        name: null
      }
    }
  },
  created() {
    this.rewardId = this.$route.query.rewardId;
  },
  mounted() {
    this.getReward();
  },
  methods: {
    getReward() {
      this.$http({
        url: `http://localhost:8080/api/rewards/${this.rewardId}`,
        method: 'GET'
      }).then((result) => {
        console.log(result.data)
        const data = result.data
        this.reward.id = data.id;
        this.reward.title = data.title;
        this.reward.description = data.description;
        this.reward.point = data.point;
        this.reward.name = data.member.name;
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    }
  }
}
</script>

<style scoped>

</style>