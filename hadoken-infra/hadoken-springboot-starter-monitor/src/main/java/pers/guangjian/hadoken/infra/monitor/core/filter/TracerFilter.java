package pers.guangjian.hadoken.infra.monitor.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import pers.guangjian.hadoken.common.util.monitor.TracerUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Trace 过滤器，打印 traceId 到 header 中返回
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/03/02 10:51
 */
public class TracerFilter extends OncePerRequestFilter {

    /**
     * Header 名 - 链路追踪编号
     */
    private static final String HEADER_NAME_TRACE_ID = "trace-id";

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 设置响应 traceId
        response.addHeader(HEADER_NAME_TRACE_ID, TracerUtils.getTraceId());

        // 继续过滤
        chain.doFilter(request, response);
    }

}
