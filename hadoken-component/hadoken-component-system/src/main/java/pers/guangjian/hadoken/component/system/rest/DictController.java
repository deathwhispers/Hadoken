/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pers.guangjian.hadoken.component.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import pers.guangjian.hadoken.component.log.annotation.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guangjian.hadoken.common.exception.BadRequestException;
import pers.guangjian.hadoken.component.system.domain.Dict;
import pers.guangjian.hadoken.component.system.service.DictService;
import pers.guangjian.hadoken.component.system.service.dto.DictQueryCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：字典管理")
@RequestMapping("/sys/dict")
public class DictController {

    private final DictService dictService;
    private static final String ENTITY_NAME = "dict";

    @Log("导出字典数据")
    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@hadoken.check('dict:list')")
    public void download(HttpServletResponse response, DictQueryCriteria criteria) throws IOException {
        dictService.download(dictService.queryAll(criteria), response);
    }

    @Log("查询字典")
    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("@hadoken.check('dict:list')")
    public ResponseEntity<Object> queryAll(){
        return new ResponseEntity<>(dictService.queryAll(new DictQueryCriteria()),HttpStatus.OK);
    }

    @Log("查询字典")
    @ApiOperation("查询字典")
    @GetMapping
    @PreAuthorize("@hadoken.check('dict:list')")
    public ResponseEntity<Object> query(DictQueryCriteria resources, Pageable pageable){
        return new ResponseEntity<>(dictService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增字典")
    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("@hadoken.check('dict:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Dict resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        dictService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改字典")
    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("@hadoken.check('dict:edit')")
    public ResponseEntity<Object> update(@Validated(Dict.Update.class) @RequestBody Dict resources){
        dictService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除字典")
    @ApiOperation("删除字典")
    @DeleteMapping
    @PreAuthorize("@hadoken.check('dict:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        dictService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}