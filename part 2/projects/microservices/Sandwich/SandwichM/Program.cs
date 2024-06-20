using Microsoft.EntityFrameworkCore;
using Sandwich.Contracts;
using Sandwich.Controllers;
using Sandwich.Entities.Context;
using Sandwich.Repository;
using Sandwich.Service;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<SandwichDbContext>();
builder.Services.AddScoped<ISandwichRepository, SandwichRepository>();
builder.Services.AddScoped<SandwichService>();
builder.Services.AddGraphQLServer().AddQueryType<SandwichQueryController>().AddMutationType<SandwichMutationController>();

var app = builder.Build();

using (var scope = app.Services.CreateScope())
{
    var services = scope.ServiceProvider;
    var context = services.GetRequiredService<SandwichDbContext>();
    if (context.Database.GetPendingMigrations().Any())
    {
        context.Database.Migrate();
    }
}

// Configure the HTTP request pipeline.
app.UseDeveloperExceptionPage();

app.UseWebSockets();

app.UseRouting();

app.UseEndpoints(endpoints =>
{
    endpoints.MapGraphQL("/graphql");
});
app.Run();