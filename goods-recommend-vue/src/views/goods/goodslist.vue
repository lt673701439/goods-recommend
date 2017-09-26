<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="标题" v-model="listQuery.title">
      </el-input>

      <!-- <el-select clearable style="width: 90px" class="filter-item" v-model="listQuery.importance" placeholder="重要性">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item">
        </el-option>
      </el-select> -->

      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.country" placeholder="原产地">
        <el-option v-for="item in  calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key">
        </el-option>
      </el-select>

      <el-select @change='handleFilter' style="width: 130px" class="filter-item" v-model="listQuery.sort" placeholder="排序">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
      <!-- <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button> -->
      <el-button class="filter-item" type="primary" icon="document" @click="handleDownload">导出</el-button>
      <el-checkbox class="filter-item" @change='tableKey=tableKey+1' v-model="showAuditor">显示原始价格</el-checkbox>
    </div>

    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 100%">

      <el-table-column align="center" label="序号" width="65">
        <template scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="更新时间">
        <template scope="scope">
          <span>{{scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="商品名称">
        <template scope="scope">
          <span class="link-type" @click="handleView(scope.row)">{{scope.row.name}}</span>
          <el-tag>{{scope.row.country}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="当前价格">
        <template scope="scope">
          <span>{{scope.row.price}}</span>
        </template>
      </el-table-column>

      <el-table-column width="110px" v-if='showAuditor' align="center" label="原始价格">
        <template scope="scope">
          <span style='color:red;'>{{scope.row.originalPrice}}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="价格走势" width="90">
        <template scope="scope">
          <el-tag :type="scope.row.priceStatus | statusFilter">{{scope.row.priceStatus}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="相关商品" width="95">
        <template scope="scope">
          <!--<span class="link-type" @click='handleFetchSyblings(scope.row.goodsid)'>{{scope.row.syblings}}</span>-->
          <span>{{scope.row.syblings}}</span>
        </template>
      </el-table-column>

      <!--
      <el-table-column width="80px" label="重要性">
        <template scope="scope">
          <icon-svg v-for="n in +scope.row.importance" icon-class="wujiaoxing" class="meta-item__icon" :key="n"></icon-svg>
        </template>
      </el-table-column>

      -->

      <el-table-column align="center" label="操作" width="150">
        <template scope="scope">
          <el-button v-if="scope.row.status!='published'" size="small" type="success" @click="handleModifyStatus(scope.row,'published')">关注
          </el-button>
          <el-button v-if="scope.row.status!='draft'" size="small" @click="handleModifyStatus(scope.row,'draft')">收藏
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

      <el-form class="small-space" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        
        <el-form-item label="更新时间">
          <el-input v-model="temp.updateTime" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="采集时间">
          <el-input v-model="temp.updateDate" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="商品名称">
          <el-input v-model="temp.name" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="原产地">
          <el-input v-model="temp.country" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="当前价格">
          <el-input v-model="temp.price" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="原始价格">
          <el-input v-model="temp.originalPrice" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="库存量">
          <el-input v-model="temp.stock" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="商品特点">
          <el-input v-model="temp.feature" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="商品品牌">
          <el-input v-model="temp.brandName" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="卖家名称">
          <el-input v-model="temp.sellerName" readonly="true"></el-input>
        </el-form-item>

        <el-form-item label="描述">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="temp.desc">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!-- <el-button v-if="dialogStatus=='create'" type="primary" @click="create">确 定</el-button>
        <el-button v-else type="primary" @click="update">确 定</el-button> -->
      </div>
    </el-dialog>

    <el-dialog title="相关商品" :visible.sync="dialogPvVisible" size="small">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="goodsid" label="商品ID"> </el-table-column>
        <el-table-column prop="name" label="商品名称"> </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { fetchList, fetchSyblings } from '@/api/goods'
import waves from '@/directive/waves.js'// 水波纹指令
import { parseTime } from '@/utils'

const calendarTypeOptions = [
      { key: '中国', display_name: '中国' },
      { key: '美国', display_name: '美国' },
      { key: '日本', display_name: '日本' },
      { key: '法国', display_name: '法国' },
      { key: '韩国', display_name: '韩国' },
      { key: '德国', display_name: '德国' },
      { key: '英国', display_name: '英国' },
      { key: '香港', display_name: '香港' },
      { key: '台湾', display_name: '台湾' },
      { key: '瑞士', display_name: '瑞士' },
      { key: '意大利', display_name: '意大利' },
      { key: '加拿大', display_name: '加拿大' }
]

// arr to obj
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'table_demo',
  directives: {
    waves
  },
  data() {
    return {
      list: null,
      total: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: '',
        title: '',
        country: '',
        sort: ''
      },
      temp: {
        id: undefined,
        importance: 0,
        remark: '',
        timestamp: 0,
        title: '',
        country: '',
        status: 'published'
      },
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: '按时间降序', key: '' }, { label: '按ID降序', key: 'id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建',
        view: '查看'
      },
      dialogPvVisible: false,
      pvData: [],
      showAuditor: false,
      tableKey: 0
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        '上涨': 'success',
        '平稳': 'gray',
        '下降': 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    timeFilter(time) {
      if (!time[0]) {
        this.listQuery.start = undefined
        this.listQuery.end = undefined
        return
      }
      this.listQuery.start = parseInt(+time[0] / 1000)
      this.listQuery.end = parseInt((+time[1] + 3600 * 1000 * 24) / 1000)
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    handleView(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'view'
      this.dialogFormVisible = true
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    handleDelete(row) {
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      const index = this.list.indexOf(row)
      this.list.splice(index, 1)
    },
    create() {
      this.temp.id = parseInt(Math.random() * 100) + 1024
      this.temp.timestamp = +new Date()
      this.temp.author = '原创作者'
      this.list.unshift(this.temp)
      this.dialogFormVisible = false
      this.$notify({
        title: '成功',
        message: '创建成功',
        type: 'success',
        duration: 2000
      })
    },
    update() {
      this.temp.timestamp = +this.temp.timestamp
      for (const v of this.list) {
        if (v.id === this.temp.id) {
          const index = this.list.indexOf(v)
          this.list.splice(index, 1, this.temp)
          break
        }
      }
      this.dialogFormVisible = false
      this.$notify({
        title: '成功',
        message: '更新成功',
        type: 'success',
        duration: 2000
      })
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 0,
        remark: '',
        timestamp: 0,
        title: '',
        status: 'published',
        country: ''
      }
    },
    handleFetchSyblings(goodsid) {
      fetchSyblings(goodsid).then(response => {
        this.pvData = response.data
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      require.ensure([], () => {
        const { export_json_to_excel } = require('vendor/Export2Excel')
        const tHeader = ['时间', '地区', '类型', '标题', '重要性']
        const filterVal = ['timestamp', 'province', 'country', 'title', 'importance']
        const data = this.formatJson(filterVal, this.list)
        export_json_to_excel(tHeader, data, 'table数据')
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
