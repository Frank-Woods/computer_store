<#macro template page>
    <div class="row">
        <div class="col-sm-12 col-md-5">
            <#assign startIndex = (page.content?size == 0)?then(0, page.number * page.size + 1)>
            <#assign delta = (1 <= page.content?size / page.size / (page.number + 1))?then(page.size, page.content?size % page.size)>
            <#assign lastIndex = (page.content?size == 0)?then(0, startIndex + delta - 1)>
            <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Показано от ${startIndex} до ${lastIndex} из ${page.content?size}</div>
        </div>
        <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                <ul class="pagination">
                    <li class="paginate_button page-item previous <#if page.pageable.getPageNumber() == 0>disabled</#if>">
                        <button class="page-link" <#if page.pageable.getPageNumber() == 0>disabled<#else>onclick="createPageInput(${page.pageable.getPageNumber() - 1})"</#if>>Предыдущая</button>
                    </li>
                    <#assign lastPage = (page.content?size / page.size)?ceiling - 1>
                    <#assign visiblePages = 7>
                    <#assign currentPage = page.number>
                    <#assign deltaLast = lastPage - currentPage>
                    <#if lastPage <= visiblePages>
                        <#list 0..lastPage as index>
                            <#if index < 0><#break></#if>
                            <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                <button class="page-link" <#if currentPage == index>disabled<#else>onclick="createPageInput(${index})"</#if>>${index + 1}</button>
                            </li>
                        </#list>
                    <#else>
                        <li class="paginate_button page-item <#if currentPage == 0>active</#if>">
                            <button class="page-link" <#if currentPage == 0>disabled<#else>onclick="createPageInput(${0})"</#if>>1</button>
                        </li>
                        <#if currentPage < (visiblePages / 2)?floor>
                            <#list 1..currentPage + visiblePages - currentPage - 2 as index>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <button class="page-link" <#if currentPage == index>disabled<#else>onclick="createPageInput(${index})"</#if>>${index + 1}</button>
                                </li>
                            </#list>
                        <#elseif deltaLast < (visiblePages / 2)?floor>
                            <#list currentPage - visiblePages + deltaLast + 2..lastPage - 1 as index>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <button class="page-link" <#if currentPage == index>disabled<#else>onclick="createPageInput(${index})"</#if>>${index + 1}</button>
                                </li>
                            </#list>
                        <#else>
                            <#list currentPage - (visiblePages / 2)?floor + 1..currentPage + (visiblePages / 2)?floor - 1 as index>
                                <#if index < 1><#continue></#if>
                                <#if lastPage - 1 <= index><#break></#if>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <button class="page-link" <#if currentPage == index>disabled<#else>onclick="createPageInput(${index})"</#if>>${index + 1}</button>
                                </li>
                            </#list>
                        </#if>
                        <#if lastPage != 0>
                            <li class="paginate_button page-item <#if currentPage == lastPage>active</#if>">
                                <button class="page-link" <#if currentPage == lastPage>disabled<#else>onclick="createPageInput(${lastPage})"</#if>>${lastPage + 1}</button>
                            </li>
                        </#if>
                    </#if>
                    <li class="paginate_button page-item <#if page.totalPages <= page.pageable.getPageNumber() + 1>disabled</#if>">
                        <button class="page-link" <#if page.totalPages <= page.pageable.getPageNumber() + 1>disabled<#else>onclick="createPageInput(${page.pageable.getPageNumber() + 1})"</#if>>Следующая</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</#macro>