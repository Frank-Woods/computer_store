<#macro template page>
    <div class="row">
        <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Показано от ${page.number * page.size + 1} до ${page.number * page.numberOfElements + page.numberOfElements} из ${page.totalElements}</div>
        </div>
        <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                <ul class="pagination">
                    <li class="paginate_button page-item previous <#if page.pageable.getPageNumber() == 0>disabled</#if>">
                        <button class="page-link" <#if page.pageable.getPageNumber() == 0>disabled<#else>onclick="createPageInput(${page.pageable.getPageNumber() - 1})"</#if>>Предыдущая</button>
                    </li>
                    <#assign lastPage = page.totalPages - 1>
                    <#assign visiblePages = 7>
                    <#assign currentPage = page.pageable.getPageNumber()>
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